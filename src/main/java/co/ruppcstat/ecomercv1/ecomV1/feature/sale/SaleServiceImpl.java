package co.ruppcstat.ecomercv1.ecomV1.feature.sale;

import co.ruppcstat.ecomercv1.ecomV1.deman.Product;
import co.ruppcstat.ecomercv1.ecomV1.deman.Sale;
import co.ruppcstat.ecomercv1.ecomV1.deman.Staff;
import co.ruppcstat.ecomercv1.ecomV1.feature.product.ProductRepository;
import co.ruppcstat.ecomercv1.ecomV1.feature.sale.dtoSale.SaleCreate;
import co.ruppcstat.ecomercv1.ecomV1.feature.sale.dtoSale.SaleResponse;
import co.ruppcstat.ecomercv1.ecomV1.feature.sale.dtoSale.SaleUpdate;
import co.ruppcstat.ecomercv1.ecomV1.feature.staff.StaffRepository;
import co.ruppcstat.ecomercv1.ecomV1.mapper.SaleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SalService{
    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;
    private final StaffRepository staffRepository;
    private final ProductRepository productRepository;
    @Override
    public SaleResponse createSale(SaleCreate saleCreate) {
        Staff staff=staffRepository.findByStaffId(saleCreate.staffId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"staff phone number not found"));

//        Product product;

        List<String> keyIdList = saleCreate.keyId();
        List<Product> productList = new java.util.ArrayList<>(List.of());

        for (int i = 0;i<keyIdList.size();i++) {
            System.out.println(keyIdList.get(i));
            Product product = productRepository.findByKeyId(keyIdList.get(i))
                    .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"product key not found"));
            productList.add(product);
        }
//        // test program...................
        Sale sale = saleMapper.createSale(saleCreate);
        sale.setStaff(staff);
        sale.setProducts(productList);
        sale.setCodeSale(UUID.randomUUID().toString());
        sale.setSaleDate(LocalDate.now());
        sale.setIsDeleted(false);
        sale= saleRepository.save(sale);
        return saleMapper.entityToResponse(sale);
//        return null;
    }

    @Override
    public SaleResponse updateSale(String codeSale, SaleUpdate saleUpdate) {
        return null;
    }

    @Override
    public void deleteSale(String codeSale) {
        Sale sale=saleRepository.findByCodeSale(codeSale)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"sale not found"));
        saleRepository.delete(sale);

    }

    @Override
    public SaleResponse getSale(String codeSale) {
        Sale sale=saleRepository.findByCodeSale(codeSale)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"sale not found"));
       sale= saleRepository.save(sale);
        return saleMapper.entityToResponse(sale);
    }

    @Override
    public Page<SaleResponse> getSales(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "saleId");
        PageRequest pageRequest=PageRequest.of(pageNumber, pageSize, sortById);
        Page<Sale> sales=saleRepository.findAll(pageRequest);
        return sales.map(saleMapper::entityToResponse);
    }

    @Override
    public SaleResponse isDeletedSale(String codeSale) {
        Sale sale=saleRepository.findByCodeSale(codeSale)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"sale code not found"));
        sale.setIsDeleted(true);
       sale= saleRepository.save(sale);
        return saleMapper.entityToResponse(sale);
    }
}
