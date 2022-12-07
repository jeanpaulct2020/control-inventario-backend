package com.mycompany.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponseRest extends ResponseRest{

    private CategoryResponse categoryResponse = new CategoryResponse();
}
