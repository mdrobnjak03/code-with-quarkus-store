package rest.server;

import exception.UserException;
import jakarta.ws.rs.*;
import org.jboss.resteasy.reactive.RestResponse;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import jakarta.inject.Inject;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.User;
import org.eclipse.microprofile.openapi.annotations.Operation;
import service.UserRequest;
import service.UserService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@jakarta.ws.rs.Path("/api/users")
public class UserRest {

    private static final String UPLOAD_DIR = "images/user";

    @Inject
    UserService userService;

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @jakarta.ws.rs.Path("/")
    @Operation(summary = "Web server that creates new user and uploads a image of him.",
            description = "User has to be unique.")
    public Response createUser(UserRequest request) {
        User user = request.getUser();
        FileUpload image = request.getImage();

        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) directory.mkdirs();

        String fileName = System.currentTimeMillis() + image.fileName();
        Path newFilePath = Paths.get(UPLOAD_DIR + "/" + fileName);
        try {
            Files.move(image.uploadedFile(), newFilePath);
            user.setImage(fileName);
            return Response.ok(userService.createUser(user)).build();
        } catch (IOException e) {
            return Response.serverError().entity("Error saving file: " + e.getMessage()).build();
        } catch (UserException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @jakarta.ws.rs.Path("/")
    public Response getAllUsers() {
        List<User> users = userService.getAllUsers();
        return Response.ok().entity(users).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @jakarta.ws.rs.Path("/search")
    public Response getUsersByEmail(@QueryParam(value = "email") String email) {
        List<User> users = userService.getUsersByEmail(email);

        if (users.isEmpty()) {
            return Response.status(RestResponse.Status.NOT_FOUND).entity("No users found with the given email.").build();
        }
        User user = users.get(0);
        Path filePath = Paths.get(UPLOAD_DIR + "/" + user.getImage());
        byte[] fileContent;
        try {
            fileContent = Files.readAllBytes(filePath);
        } catch (IOException e) {
            return Response.serverError().entity("Error reading file: " + e.getMessage()).build();
        }
        String fileName = filePath.getFileName().toString();
        return Response.ok(fileContent)
                .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @jakarta.ws.rs.Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
       return userService.deleteUser(id)
               ? Response.noContent().build()
               : Response.status(Response.Status.NOT_FOUND).entity("User not found.").build();
    }


}
