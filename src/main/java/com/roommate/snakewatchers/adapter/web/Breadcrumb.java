package com.roommate.snakewatchers.adapter.web;

import lombok.Getter;
import lombok.Setter;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Breadcrumb {
    private String label;
    private String url;

    public Breadcrumb(String label, String url) {
        this.label = label;
        this.url = url;
    }

    public static void breadcrumb(Model model) {
        List<Breadcrumb> breadcrumbs = Arrays.asList(
                new Breadcrumb("Zeitauswahl", "/booking/search"),
                new Breadcrumb("Raum", "/booking/search/overview/room"),
                new Breadcrumb("Arbeitsplatz", "/booking/search/overview/room/workplace")
        );

        model.addAttribute("breadcrumbs", breadcrumbs);
    }

    public static void breadcrumbTime(Model model) {
        List<Breadcrumb> breadcrumbs = Arrays.asList(
                new Breadcrumb("Zeitauswahl", "/booking/search")
        );

        model.addAttribute("breadcrumbs", breadcrumbs);
    }

    public static void breadcrumbRoom(Model model) {
        List<Breadcrumb> breadcrumbs = Arrays.asList(
                new Breadcrumb("Zeitauswahl", "/booking/search"),
                new Breadcrumb("Raum", "/booking/search/overview/room")
        );

        model.addAttribute("breadcrumbs", breadcrumbs);
    }

    public static void breadcrumbSummary(Model model) {
        List<Breadcrumb> breadcrumbs = Arrays.asList(
                new Breadcrumb("Zeitauswahl", "/booking/search"),
                new Breadcrumb("Raum", "/booking/search/overview/room"),
                new Breadcrumb("Arbeitsplatz", "/booking/search/overview/room/workplace"),
                new Breadcrumb("Übersicht", "/booking/search/overview/room/workplace")

        );

        model.addAttribute("breadcrumbs", breadcrumbs);
    }
    // Getter und Setter
}
