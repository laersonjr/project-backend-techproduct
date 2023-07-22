package com.laerson.techsolutio.techproduct.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "tb_user")
public class User {

    @EqualsAndHashCode.Include
    @Id
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    public void setRandomId() {
        this.id = UUID.randomUUID();
    }

}
