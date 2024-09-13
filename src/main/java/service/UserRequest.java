package service;

import model.User;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jboss.resteasy.reactive.PartType;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import jakarta.ws.rs.core.MediaType;


public class UserRequest {
    @RestForm("image")
    @PartType(MediaType.APPLICATION_OCTET_STREAM)
    @Schema(implementation = MultipartResource.UploadSchema.class)
    private FileUpload image;

    @RestForm("user")
    @PartType(MediaType.APPLICATION_JSON)
    private User user;

    public FileUpload getImage() {
        return image;
    }

    public void setImage(FileUpload image) {
        this.image = image;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

