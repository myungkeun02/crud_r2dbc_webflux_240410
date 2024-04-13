package org.myungkeun.crud_r2dbc_webflux_240410.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table("public.blog")

public class Blog extends BaseEntity {
    @Column("title")
    private String title;

    @Column("description")
    private String description;

    @Column("category")
    private String category;
}
