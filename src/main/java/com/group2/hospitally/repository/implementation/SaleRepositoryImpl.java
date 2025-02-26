package com.group2.hospitally.repository.implementation;

import com.group2.hospitally.mapper.SaleRowMapper;
import com.group2.hospitally.model.entity.Sale;
import com.group2.hospitally.repository.Interface.SaleRepository;
import com.group2.hospitally.repository.query.MedicationQuery;
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
    public List<Sale> getSaleByMedicationId(int medicationId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("medicationId", medicationId);
        return jdbcTemplate.query(SaleQuery.GET_SALE_BY_MEDICATION, parameterSource, new SaleRowMapper());
    }

    @Override
    public List<Sale> getSaleByPatientId(int patientId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("patientId", patientId);
        return jdbcTemplate.query(SaleQuery.GET_SALE_BY_PATIENT, parameterSource, new SaleRowMapper());
    }

    @Override
    public List<Sale> getSaleByHospital(int hospitalId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("hospitalId", hospitalId);
        return jdbcTemplate.query(SaleQuery.GET_SALE_BY_HOSPITAL_ID, parameterSource, new SaleRowMapper());
    }

    @Override
    public List<Sale> getAllSales() {
        return jdbcTemplate.query(SaleQuery.GET_ALL_SALES, new SaleRowMapper());
    }

    @Override
    public Sale createSale(Sale sale) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("medicationId", sale.getMedicationId())
                .addValue("patientId", sale.getPatientId())
                .addValue("saleQuantity", sale.getSaleQuantity())
                .addValue("saleTotalPrice", sale.getSaleTotalPrice());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(SaleQuery.INSERT_SALE, parameterSource, keyHolder, new String[]{"saleId"});

        // Get the generated ID and set it in the patient object
        int saleId = keyHolder.getKey().intValue();
        sale.setSaleId(saleId);

        return sale;
    }

    @Override
    public Sale updateSale(Sale sale) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("saleId", sale.getSaleId())
                .addValue("medicationId", sale.getMedicationId())
                .addValue("patientId", sale.getPatientId())
                .addValue("saleQuantity", sale.getSaleQuantity())
                .addValue("saleTotalPrice", sale.getSaleTotalPrice());

        jdbcTemplate.update(SaleQuery.UPDATE_SALE_BY_ID, parameterSource);

        return getSaleById(sale.getSaleId());
    }

    @Override
    public int deleteSaleById(int saleId) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("saleId", saleId);
        return jdbcTemplate.update(SaleQuery.DELETE_SALE_BY_ID, parameterSource);
    }
}