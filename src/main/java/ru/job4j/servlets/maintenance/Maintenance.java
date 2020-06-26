package ru.job4j.servlets.maintenance;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.servlets.domain.Seat;

import java.util.ArrayList;
import java.util.List;

public class Maintenance {

    public List<Seat> getSeatsList() {
        List<Seat> result = new ArrayList<>();
        result.add(new Seat(1, 1, 1000, true));
        result.add(new Seat(1, 2, 1000, false));
        result.add(new Seat(1, 3, 1000, false));
        result.add(new Seat(2, 1, 500, false));
        result.add(new Seat(2, 2, 500, false));
        result.add(new Seat(2, 1, 500, false));
        result.add(new Seat(3, 1, 500, false));
        result.add(new Seat(3, 2, 500, false));
        result.add(new Seat(3, 3, 500, false));
        return result;
    }

    public String getJson(List<Seat> list) {
        String result = null;
        JSONArray json = new JSONArray(list);
        result = json.toString();
        return result;
    }

    public static void main(String[] args) {
        Maintenance maintenance = new Maintenance();
        List<Seat> seats = maintenance.getSeatsList();
        System.out.println(maintenance.getJson(seats));
    }
}
