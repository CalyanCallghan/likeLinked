package com.onpassive.onet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onpassive.onet.model.AccountModel;


@Repository
public interface AccountsRepository extends JpaRepository<AccountModel, String> {



}
