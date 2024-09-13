package rest.server;

import exception.ProductException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Product;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.resteasy.reactive.RestResponse;
import service.ProductService;

import java.util.List;

@Path("/api/products")
public class ProductRest {
    @Inject
    ProductService productService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    @Operation(summary = "Web server that creates new product.",
            description = "Product has to be unique.")
    public Response createProduct(Product product) {
        Product newProduct;
        try {
            newProduct = productService.createProduct(product);
        } catch (ProductException e) {
            return Response.status(RestResponse.Status.CONFLICT).entity(e.getMessage()).build();
        }
        return Response.ok().entity(newProduct).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/")
    public Response getAllProducts() {
        List<Product> vehicles =  productService.getAllProducts();
        return Response.ok().entity(vehicles).build();
    }
}
