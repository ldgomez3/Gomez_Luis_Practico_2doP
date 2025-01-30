package com.practico.core.miapi.repository;

import java.util.List;
import java.util.Optional;

import com.practico.core.miapi.model.Branch;

public interface BranchRepository {

    List<Branch> findAll();

    Branch save(Branch branch);

    Optional<Branch> findById(String id);

}
