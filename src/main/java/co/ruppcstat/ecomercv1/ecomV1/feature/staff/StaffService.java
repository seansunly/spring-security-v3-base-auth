package co.ruppcstat.ecomercv1.ecomV1.feature.staff;

import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffDTO.StaffCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffDTO.StaffResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffDTO.StaffUpdate;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StaffService {
    StaffResponse createStaff(StaffCreate staffCreate);
    StaffResponse updateStaff(String phone,StaffUpdate staffUpdate);
    void deleteStaff(String phone);
    StaffResponse isStopWork(String phone);
    StaffResponse getStaff(String phone);
    Page<StaffResponse> getAllStaff(int pageNumber, int pageSize);
}
