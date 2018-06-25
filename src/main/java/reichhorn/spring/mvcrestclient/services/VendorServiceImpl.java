package reichhorn.spring.mvcrestclient.services;

import org.springframework.stereotype.Service;
import reichhorn.spring.mvcrestclient.api.v1.mapper.VendorMapper;
import reichhorn.spring.mvcrestclient.api.v1.model.VendorDTO;
import reichhorn.spring.mvcrestclient.controllers.v1.VendorController;
import reichhorn.spring.mvcrestclient.model.Vendor;
import reichhorn.spring.mvcrestclient.repositories.VendorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {

    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public List<VendorDTO> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(vendor -> {
                    VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
                    vendorDTO.setVendorUrl(getVendorUrl(vendor.getId()));
                    return vendorDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .map(vendorDTO -> {
                    vendorDTO.setVendorUrl(getVendorUrl(id));
                    return vendorDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveAndReturnDTO(vendorMapper.venderDtoToVendor(vendorDTO));
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);

        VendorDTO vendorDtoToReturn = vendorMapper.vendorToVendorDTO(savedVendor);
        vendorDtoToReturn.setVendorUrl(getVendorUrl(savedVendor.getId()));

        return vendorDtoToReturn;
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendorDTO) {
        Vendor vendor = vendorMapper.venderDtoToVendor(vendorDTO);
        vendor.setId(id);

        return saveAndReturnDTO(vendor);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        // only update properties that are passed in

        return vendorRepository.findById(id).map(vendor -> {

            if(vendorDTO.getName() != null) {
                vendor.setName(vendorDTO.getName());
            }

            return saveAndReturnDTO(vendor);
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }

    private String getVendorUrl(Long id) {
        return VendorController.BASE_URL + id;
    }
}
