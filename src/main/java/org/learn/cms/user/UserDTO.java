package org.learn.cms.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private long id;

    @NotEmpty
    @NotNull
    @Size(max = 75)
    public String name;

    @NotEmpty
    @NotNull
    @Email
    @Size(max = 75)
    public String email;

    @NotEmpty
    @NotNull
    @Length(min = 8, max = 16)
    public String password;

    @Size(max = 500)
    public String about;
    public String profilePic;
    private boolean enabled;
    private boolean emailVerified;
    private Providers provider = Providers.SELF;
    private String userProviderId;
}
