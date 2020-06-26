package ru.job4j.servlets.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.servlets.domain.Account;
import ru.job4j.servlets.domain.MovieSession;
import ru.job4j.servlets.domain.Seat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PsqlStore implements Store {

    private final static Logger LOGGER = Logger.getLogger(PsqlStore.class.getName());

    private final BasicDataSource pool = new BasicDataSource();

    public PsqlStore() {
        init();
    }

    private void init() {
        Properties cfg = new Properties();
        try (BufferedReader io = new BufferedReader(
                new FileReader("db.properties")
        )) {
            cfg.load(io);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        pool.setDriverClassName(cfg.getProperty("jdbc.driver"));
        pool.setUrl(cfg.getProperty("jdbc.url"));
        pool.setUsername(cfg.getProperty("jdbc.username"));
        pool.setPassword(cfg.getProperty("jdbc.password"));
        pool.setMinIdle(5);
        pool.setMaxIdle(10);
        pool.setMaxOpenPreparedStatements(100);
    }

    private static final class Lazy {
        private static final Store INST = new PsqlStore();
    }

    public static Store instOf() {
        return Lazy.INST;
    }

    @Override
    public void saveAccount(Account account) {
        if (account.getId() == 0) {
            createAccount(account);
        } else {
            updateAccount(account);
        }
    }

    private void createAccount(Account account) {
        Savepoint savepoint = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO accounts (user_name,"
                             + "phone_number) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            pool.getConnection().setAutoCommit(false);

            ps.setString(1, account.getLogin());
            ps.setString(2, account.getEmail());

            savepoint = pool.getConnection().setSavepoint();

            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    account.setId(id.getInt(1));
                }
            }
            pool.getConnection().commit();
        } catch (Exception e) {
            try {
                pool.getConnection().rollback(savepoint);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Transaction failed", ex);
            }
            LOGGER.log(Level.SEVERE, "Failed connecting to DB", e);
        }
    }

    private void updateAccount(Account account) {
        Savepoint savepoint = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("UPDATE accounts SET user_name = ?,"
                             + " phone_number = ? WHERE id = ?", PreparedStatement.RETURN_GENERATED_KEYS)) {
            pool.getConnection().setAutoCommit(false);

            ps.setString(1, account.getLogin());
            ps.setString(2, account.getEmail());
            ps.setInt(3, account.getId());

            savepoint = pool.getConnection().setSavepoint();

            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    account.setId(id.getInt(1));
                }
            }
            pool.getConnection().commit();
        } catch (Exception e) {
            try {
                pool.getConnection().rollback(savepoint);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Transaction failed", ex);
            }
            LOGGER.log(Level.SEVERE, "Failed connecting to DB", e);
        }
    }

    @Override
    public void saveMovieSession(MovieSession movieSession) {
        if (movieSession.getId() == 0) {
            createMovieSession(movieSession);
        } else {
            updateMovieSession(movieSession);
        }
    }

    private void createMovieSession(MovieSession movieSession) {
        Savepoint savepoint = null;
        JSONObject seats = new JSONObject(movieSession.getSeats());
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("INSERT INTO moviesessions (data,"
                     + "film_name, hall_id, session_time, seats) VALUES (?, ?, ?, ?, ?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            pool.getConnection().setAutoCommit(false);

            ps.setString(1, movieSession.getDate());
            ps.setString(2, movieSession.getFilmName());
            ps.setString(3, movieSession.getHallId());
            ps.setString(4, movieSession.getSessionTime());
            ps.setString(5, seats.toString());

            savepoint = pool.getConnection().setSavepoint();

            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    movieSession.setId(id.getInt(1));
                }
            }
            pool.getConnection().commit();
        } catch (Exception e) {
            try {
                pool.getConnection().rollback(savepoint);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Transaction failed", ex);
            }
            LOGGER.log(Level.SEVERE, "Failed connecting to DB", e);
        }
    }

    private void updateMovieSession(MovieSession movieSession) {
        Savepoint savepoint = null;
        JSONObject seats = new JSONObject(movieSession.getSeats());
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("UPDATE moviesessions SET data = ?,"
                     + " film_name = ?, hall_id = ?, session_time = ?, seats = ? WHERE id = ?",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            pool.getConnection().setAutoCommit(false);

            ps.setString(1, movieSession.getDate());
            ps.setString(2, movieSession.getFilmName());
            ps.setString(3, movieSession.getHallId());
            ps.setString(4, movieSession.getSessionTime());
            ps.setString(5, seats.toString());
            ps.setInt(6, movieSession.getId());

            savepoint = pool.getConnection().setSavepoint();

            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    movieSession.setId(id.getInt(1));
                }
            }
            pool.getConnection().commit();
        } catch (Exception e) {
            try {
                pool.getConnection().rollback(savepoint);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Transaction failed", ex);
            }
            LOGGER.log(Level.SEVERE, "Failed connecting to DB", e);
        }
    }

    @Override
    public void deleteAccount(int id) {
        Savepoint savepoint = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("DELETE FROM accounts WHERE id = ?",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            pool.getConnection().setAutoCommit(false);

            ps.setInt(1, id);

            savepoint = pool.getConnection().setSavepoint();

            ps.execute();

            pool.getConnection().commit();
        } catch (Exception e) {
            try {
                pool.getConnection().rollback(savepoint);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Transaction failed", ex);
            }
            LOGGER.log(Level.SEVERE, "Failed connecting to DB", e);
        }
    }

    @Override
    public void deleteMovieSession(int id) {
        Savepoint savepoint = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("DELETE FROM moviesessions WHERE id = ?",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            pool.getConnection().setAutoCommit(false);

            ps.setInt(1, id);

            savepoint = pool.getConnection().setSavepoint();

            ps.execute();

            pool.getConnection().commit();
        } catch (Exception e) {
            try {
                pool.getConnection().rollback(savepoint);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Transaction failed", ex);
            }
            LOGGER.log(Level.SEVERE, "Failed connecting to DB", e);
        }
    }

    @Override
    public Account findAccountById(int id) {
        Account result = null;
        Savepoint savepoint = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM accounts WHERE id = ?",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            pool.getConnection().setAutoCommit(false);

            ps.setInt(1, id);

            savepoint = pool.getConnection().setSavepoint();

            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    result = new Account(it.getInt("id"), it.getString("acc_login"),
                            it.getString("email"));
                }
            }

            pool.getConnection().commit();
        } catch (Exception e) {
            try {
                pool.getConnection().rollback(savepoint);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Transaction failed", ex);
            }
            LOGGER.log(Level.SEVERE, "Failed connecting to DB", e);
        }
        return result;
    }

    @Override
    public MovieSession findMovieSession(int id) {
        MovieSession result = null;
        Savepoint savepoint = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM moviesessions WHERE id = ?",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            cn.setAutoCommit(false);

            ps.setInt(1, id);

            savepoint = cn.setSavepoint();

            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    result = new MovieSession(it.getInt("id"), it.getString("data"),
                            it.getString("film_name"), it.getString("hall_id"),
                            it.getString("session_time"), jSONtoSeat(it.getString("seats")));
                }
            }

            cn.commit();
        } catch (Exception e) {
            try {
                pool.getConnection().rollback(savepoint);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Transaction failed", ex);
            }
            LOGGER.log(Level.SEVERE, "Failed connecting to DB", e);
        }
        return result;
    }

    @Override
    public Collection<Account> findAllAccounts() {
        List<Account> result = new ArrayList<>();
        Savepoint savepoint = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM accounts",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            pool.getConnection().setAutoCommit(false);

            savepoint = pool.getConnection().setSavepoint();

            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    result.add(new Account(it.getInt("id"), it.getString("acc_login"),
                            it.getString("email")));
                }
            }

            pool.getConnection().commit();
        } catch (Exception e) {
            try {
                pool.getConnection().rollback(savepoint);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Transaction failed", ex);
            }
            LOGGER.log(Level.SEVERE, "Failed connecting to DB", e);
        }
        return result;
    }

    @Override
    public Collection<MovieSession> findAllMovieSessions() {
        List<MovieSession> result = new ArrayList<>();
        Savepoint savepoint = null;
        try (Connection cn = pool.getConnection();
             PreparedStatement ps =  cn.prepareStatement("SELECT * FROM moviesessions",
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            pool.getConnection().setAutoCommit(false);

            savepoint = pool.getConnection().setSavepoint();

            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    result.add(new MovieSession(it.getInt("id"), it.getString("data"),
                            it.getString("film_name"), it.getString("hall_id"),
                            it.getString("session_time"), jSONtoSeat(it.getString("seats"))));
                }
            }

            pool.getConnection().commit();
        } catch (Exception e) {
            try {
                pool.getConnection().rollback(savepoint);
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Transaction failed", ex);
            }
            LOGGER.log(Level.SEVERE, "Failed connecting to DB", e);
        }
        return result;
    }

    private List<Seat> jSONtoSeat(String string) {
        List<Seat> result = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(string);
        for (int i = 0; i < jsonArray.length(); i++) {
            int row = jsonArray.getJSONObject(i).getInt("row");
            int seat = jsonArray.getJSONObject(i).getInt("seat");
            int price = jsonArray.getJSONObject(i).getInt("price");
            boolean occupied = jsonArray.getJSONObject(i).getBoolean("occupied");
            result.add(new Seat(row, seat, price, occupied));
        }
        return result;
    }
}
