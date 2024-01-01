package com.roommate.snakewatchers.testdata;

import com.roommate.snakewatchers.domain.DTO.BookingDTO;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class RequestBookingBuilder {

    private final MockHttpServletRequestBuilder requestBuilder;

    private RequestBookingBuilder(MockHttpServletRequestBuilder requestBuilder) {
        this.requestBuilder = requestBuilder;
    }

    public static RequestBookingBuilder postConfirmBooking(BookingDTO booking) {
        return new RequestBookingBuilder(post("/").with(oauth2Login())
                .flashAttr("booking", booking));
    }

    public static RequestBookingBuilder postSelectBookingView(BookingDTO booking,
                                                              String dateTimeFrom,
                                                              String dateTimeTo) {
        return new RequestBookingBuilder(get("/booking/search").with(oauth2Login())
                .param("dateTimeFrom", dateTimeFrom)
                .param("dateTimeTo", dateTimeTo)
                .flashAttr("booking", booking));
    }

    public static RequestBookingBuilder getOverviewSelectedRooms(BookingDTO booking, String equip1, String equip2) {
        return new RequestBookingBuilder(get("/booking/search/overview/room").with(oauth2Login())
                .param("equipments", equip1, equip2)
                .flashAttr("booking", booking));
    }

    public static RequestBookingBuilder getOverviewWorkplaces(Long roomID, BookingDTO booking) {
        return new RequestBookingBuilder(get("/booking/search/overview/room/workplace").with(oauth2Login())
                .param("roomID", String.valueOf(roomID))
                .flashAttr("booking", booking));
    }

    public static RequestBookingBuilder getSummaryPage(Long workplaceId, BookingDTO booking) {
        return new RequestBookingBuilder(get("/booking/search/overview/room/workplace/summary").with(oauth2Login())
                .param("workplaceId", String.valueOf(workplaceId))
                .flashAttr("booking", booking));
    }

    public MockHttpServletRequestBuilder build() {
        return requestBuilder;
    }
}
