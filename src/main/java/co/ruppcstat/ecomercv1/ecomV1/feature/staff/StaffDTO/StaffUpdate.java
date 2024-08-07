package co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffDTO;

import java.time.LocalDate;

public record StaffUpdate(
        String image,
        //@NotNull(message = "input your name khmer")
        String nameKH,
        //@NotNull(message = "input your name En")
        String nameEN,
        String gender,
        // @NotNull(message = "input brithDate ")
        LocalDate brithDate,
        //@NotNull(message = "input your phone number ")
        String phone,
        String address,
        String position,
//@NotNull(message = "input your salary right now ")
        Float salary,
        LocalDate hiredDate,
        Boolean stopWork
) {
}
