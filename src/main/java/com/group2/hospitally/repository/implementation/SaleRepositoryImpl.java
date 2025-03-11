package com.group2.hospitally.repository.implementation;

import com.group2.hospitally.mapper.RevenueRowMapper;
import com.group2.hospitally.mapper.SaleRowMapper;
import com.group2.hospitally.model.entity.Sale;
import com.group2.hospitally.model.response.RevenueResponse;
import com.group2.hospitally.repository.Interface.SaleRepository;
import com.group2.hospitally.repository.query.SaleQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SaleRepositoryImpl implements SaleRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public SaleRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Sale getSaleById(int saleId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("saleId", saleId);
        return jdbcTemplate.queryForObject(SaleQuery.GET_SALE_BY_ID, parameterSource, new SaleRowMapper());
    }

    @Override
    public List<Sale> getSaleByMedicationId(int saleMedicationId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("saleMedicationId", saleMedicationId);
        return jdbcTemplate.query(SaleQuery.GET_SALE_BY_MEDICATION, parameterSource, new SaleRowMapper());
    }

    @Override
    public List<Sale> getSaleByPatientId(int salePatientId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("salePatientId", salePatientId);
        return jdbcTemplate.query(SaleQuery.GET_SALE_BY_PATIENT, parameterSource, new SaleRowMapper());
    }


    @Override
    public List<Sale> getAllSales() {
        return jdbcTemplate.query(SaleQuery.GET_ALL_SALES, new SaleRowMapper());
    }


    @Override
    public List<RevenueResponse> getTotalRevenueGeneratedByMedications(int hospitalId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("medicationHospitalId", hospitalId);
        return jdbcTemplate.query(SaleQuery.GET_TOTAL_REVENUE_GENERATED_BY_MEDICATIONS,parameterSource, new RevenueRowMapper());
    }



    @Override
    public Sale createSale(Sale sale) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("saleMedicationId", sale.getSaleMedicationId())
                .addValue("salePatientId", sale.getSalePatientId())
                .addValue("saleQuantity", sale.getSaleQuantity())
                .addValue("saleTotalPrice", sale.getSaleTotalPrice());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(SaleQuery.INSERT_SALE, parameterSource, keyHolder, new String[]{"saleId"});

        // Get the generated ID and set it in the patient object
        int saleId = keyHolder.getKey().intValue();
        sale.setSaleId(saleId);

        return sale;
    }
}