package dev.weki.auth_service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserEntity {

    @MongoId(value = FieldType.OBJECT_ID)
    private String id;

    private String name;
    private String email;
    private String contact;
    private String password;

    @Builder.Default
    private Role role = Role.USER;

    @Builder.Default
    private List<String> friends = new ArrayList<>();

    @Builder.Default
    private List<String> groups = new ArrayList<>();
}
