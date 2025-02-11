package net.renzo.userservice.controller;

    import io.swagger.v3.oas.annotations.Operation;
    import io.swagger.v3.oas.annotations.Parameter;
    import io.swagger.v3.oas.annotations.links.Link;
    import io.swagger.v3.oas.annotations.links.LinkParameter;
    import io.swagger.v3.oas.annotations.media.Content;
    import io.swagger.v3.oas.annotations.responses.ApiResponse;
    import io.swagger.v3.oas.annotations.responses.ApiResponses;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import jakarta.validation.Valid;
    import net.renzo.userservice.dto.AddressDTO;
    import net.renzo.userservice.service.AddressService;
    import org.springframework.hateoas.EntityModel;
    import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("api/v1/address")
    @Tag(name = "Address", description = "Address API")
    public class AddressController {
        private final AddressService addressService;

        public AddressController(AddressService addressService) {
            this.addressService = addressService;
        }

        @Operation(summary = "Get address by user ID", description = "Retrieve the address details for a given user ID")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Successfully retrieved address",
                             links = @Link(name = "self", operationId = "getAddressByUserId",
                                           parameters = @LinkParameter(name = "id", expression = "$request.path.id"))),
                @ApiResponse(responseCode = "400", description = "Invalid user ID supplied", content = @Content),
                @ApiResponse(responseCode = "404", description = "Address not found", content = @Content)
        })
        @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<EntityModel<AddressDTO>> getAddressByUserId(@Parameter(description = "ID of the user to retrieve address", required = true) @PathVariable Long id) {
            if (id == null) {
                return ResponseEntity.badRequest().build();
            }

            AddressDTO addressDTO = addressService.getAddressByUserId(id);
            EntityModel<AddressDTO> resource = EntityModel.of(addressDTO);
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AddressController.class).getAddressByUserId(id)).withSelfRel());
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AddressController.class).updateAddress(id, addressDTO)).withRel("update"));
            return ResponseEntity.ok(resource);
        }

        @Operation(summary = "Update address", description = "Update the address details for a given user ID")
        @ApiResponses(value = {
                @ApiResponse(responseCode = "200", description = "Successfully updated address",
                             links = @Link(name = "self", operationId = "updateAddress",
                                           parameters = @LinkParameter(name = "id", expression = "$request.path.id"))),
                @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
                @ApiResponse(responseCode = "404", description = "Address not found", content = @Content)
        })
        @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<EntityModel<AddressDTO>> updateAddress(@Parameter(description = "ID of the user to update address", required = true) @PathVariable Long id, @Valid @RequestBody AddressDTO addressDTO) {

            AddressDTO updatedAddress = addressService.updateAddress(id, addressDTO);
            EntityModel<AddressDTO> resource = EntityModel.of(updatedAddress);
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AddressController.class).getAddressByUserId(id)).withRel("self"));
            resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AddressController.class).updateAddress(id, addressDTO)).withSelfRel());
            return ResponseEntity.ok(resource);
        }
    }