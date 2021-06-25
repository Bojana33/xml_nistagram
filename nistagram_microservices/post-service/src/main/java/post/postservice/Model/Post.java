package post.postservice.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Post implements Serializable {

    @Id
    private Long id;

    @Column
    private Date createdAt;

    @Column
    private Date updatedAt;

    @Column
    private String caption;

    @Column
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername() {
        this.username = username;
    }


}



