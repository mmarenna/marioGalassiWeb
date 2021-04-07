package com.tripleh.triplehapp.repository;

import com.tripleh.triplehapp.entity.Contacto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("contactoRepository")
public interface IContactoRepository extends CrudRepository<Contacto, Long> {
}
