package reichhorn.spring.mvcrestclient.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reichhorn.spring.mvcrestclient.api.v1.mapper.VendorMapper;
import reichhorn.spring.mvcrestclient.api.v1.model.VendorDTO;
import reichhorn.spring.mvcrestclient.controllers.v1.VendorController;
import reichhorn.spring.mvcrestclient.model.Vendor;
import reichhorn.spring.mvcrestclient.repositories.VendorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {

    VendorService vendorService;

    @Mock
    VendorRepository vendorRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(VendorMapper.INSTANCE, vendorRepository);
    }


    @Test
    public void getAllVendors() {
        // given
        List<Vendor> vendorList = Arrays.asList(new Vendor(), new Vendor());

        when(vendorRepository.findAll()).thenReturn(vendorList);

        // when
        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

        // then
        assertEquals(2, vendorDTOS.size());
    }

    @Test
    public void getVendorById() {
        // given
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Some Vendor");

        when(vendorRepository.findById(anyLong())).thenReturn(Optional.of(vendor));

        // when
        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        // then
        assertEquals("Some Vendor", vendorDTO.getName());
    }

    @Test
    public void createNewVendor() {
        // given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Another Vendor");

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        // when
        VendorDTO savedDTO = vendorService.createNewVendor(vendorDTO);

        // then
        assertEquals(vendorDTO.getName(), savedDTO.getName());
        assertEquals(VendorController.BASE_URL + "1", savedDTO.getVendorUrl());
    }

    @Test
    public void updateVendor() {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Some Vendor");

        Vendor vendor = new Vendor();
        vendor.setName("Some Vendor");
        vendor.setId(1L);

        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        //when
        VendorDTO savedVendorDTO = vendorService.updateVendor(1L, vendorDTO);

        //then
        // 'should' defaults to times = 1
        then(vendorRepository).should().save(any(Vendor.class));
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    public void patchVendor() {
        //given
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Some Vendor");

        Vendor vendor = new Vendor();
        vendor.setName("Some Vendor");
        vendor.setId(1L);

        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));
        given(vendorRepository.save(any(Vendor.class))).willReturn(vendor);

        //when
        VendorDTO savedVendorDTO = vendorService.patchVendor(1L, vendorDTO);

        //then
        // 'should' defaults to times = 1
        then(vendorRepository).should().save(any(Vendor.class));
        then(vendorRepository).should(times(1)).findById(anyLong());
        assertThat(savedVendorDTO.getVendorUrl(), containsString("1"));
    }

    @Test
    public void deleteVendorById() {
        //when
        vendorService.deleteVendorById(1L);

        //then
        then(vendorRepository).should().deleteById(anyLong());
    }
}