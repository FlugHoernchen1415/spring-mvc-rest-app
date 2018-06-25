package reichhorn.spring.mvcrestclient.api.v1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import reichhorn.spring.mvcrestclient.api.v1.model.VendorDTO;
import reichhorn.spring.mvcrestclient.model.Vendor;

@Mapper(componentModel = "spring")
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor venderDtoToVendor(VendorDTO vendorDTO);
}
