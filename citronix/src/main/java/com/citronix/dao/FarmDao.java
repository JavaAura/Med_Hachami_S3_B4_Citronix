package com.citronix.dao;

import com.citronix.dto.req.FarmDTO;
import com.citronix.mapper.FarmMapperDTO;
import com.citronix.model.Farm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class FarmDao {

    @PersistenceContext
    private EntityManager em;

    public List<FarmDTO> findFarmByNameAndAddress(String farmName, String farmAddress, int page, int size) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Farm> cq = cb.createQuery(Farm.class);

        Root<Farm> farm = cq.from(Farm.class);

        Predicate namePredicate = cb.conjunction();  // Default to true
        Predicate addressPredicate = cb.conjunction();  // Default to true

        if (farmName != null && !farmName.trim().isEmpty()) {
            namePredicate = cb.equal(farm.get("farmName"), farmName);
        }

        if (farmAddress != null && !farmAddress.trim().isEmpty()) {
            addressPredicate = cb.like(farm.get("farmAddress"), "%" + farmAddress + "%");
        }

        cq.where(cb.and(namePredicate, addressPredicate));

        // Apply pagination
        TypedQuery<Farm> query = em.createQuery(cq);
        query.setFirstResult(page * size); // Start position of the first result
        query.setMaxResults(size);        // Maximum number of results to fetch

        List<Farm> farms = query.getResultList();

        return farms.stream().map(f -> FarmMapperDTO.INSTANCE.toDTO(f)).collect(Collectors.toList());
    }

}
