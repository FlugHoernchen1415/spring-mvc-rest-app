package reichhorn.spring.mvcrestclient.api.v1.mapper;

import org.junit.Test;
import reichhorn.spring.mvcrestclient.api.v1.model.VendorDTO;
import reichhorn.spring.mvcrestclient.model.Vendor;

import static org.junit.Assert.*;

public class VendorMapperTest {

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    @Test
    public void vendorToVendorDTO() {
        // given
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Some Vendor");

        // when
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        // then
        assertEquals("Some Vendor", vendorDTO.getName());
    }

    @Test
    public void venderDtoToVendor() {
        // given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Another Vendor");

        // when
        Vendor vendor = vendorMapper.venderDtoToVendor(vendorDTO);

        // then
        assertEquals("Another Vendor", vendor.getName());
    }
}