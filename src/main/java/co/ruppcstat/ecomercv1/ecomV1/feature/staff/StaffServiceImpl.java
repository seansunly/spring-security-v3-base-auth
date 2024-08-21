package co.ruppcstat.ecomercv1.ecomV1.feature.staff;

import co.ruppcstat.ecomercv1.ecomV1.deman.Staff;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffDTO.StaffCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffDTO.StaffResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffDTO.StaffUpdate;
import co.ruppcstat.ecomercv1.ecomV1.mapper.StaffMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;

    @Override
    public StaffResponse createStaff(StaffCreate staffCreate) {
        if(staffRepository.existsByPhone(staffCreate.phone())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already exists");
        }
        Staff staff =staffMapper.createStaff(staffCreate);
        staff.setHiredDate(LocalDate.parse(LocalDate.now().toString()));
        staff.setStopWork(false);
        staff = staffRepository.save(staff);
        return staffMapper.EntityToResponse(staff);
    }

    @Override
    public StaffResponse updateStaff(String phone, StaffUpdate staffUpdate) {

        Staff staff=staffRepository.findByPhone(phone)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"phone not found"));
        staffMapper.updateStaff(staff,staffUpdate);
        staff = staffRepository.save(staff);
        return staffMapper.EntityToResponse(staff);
    }

    @Override
    public void deleteStaff(String phone) {
        Staff staff=staffRepository.findByPhone(phone).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"phone not found"));
        staffRepository.delete(staff);
    }

    @Override
    public StaffResponse isStopWork(String phone) {
        Staff staff=staffRepository.findByPhone(phone).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"phone not found"));
        staff.setStopWork(true);
        staff = staffRepository.save(staff);
        return staffMapper.EntityToResponse(staff);
    }

    @Override
    public StaffResponse getStaff(String phone) {
        Staff staff=staffRepository.findByPhone(phone).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"phone not found"));
        staff = staffRepository.save(staff);
        return staffMapper.EntityToResponse(staff);
    }

    @Override
    public Page<StaffResponse> getAllStaff(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "staffId");
        PageRequest pageRequest=PageRequest.of(pageNumber, pageSize, sortById);
        Page<Staff> staffs=staffRepository.findAll(pageRequest);
        return staffs.map(staffMapper::EntityToResponse);
    }
}
