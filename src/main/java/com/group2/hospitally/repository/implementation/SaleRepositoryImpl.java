package com.group2.hospitally.repository.implementation;

import com.group2.hospitally.mapper.SaleRowMapper;
import com.group2.hospitally.model.entity.Sale;
import com.group2.hospitally.repository.Interface.SaleRepository;
import com.group2.hospitally.repository.query.SaleQuery;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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

        jdbcTemplate.update(SaleQuery.INSERT_SALE, parameterSource);

        return sale; // You might want to fetch and return the newly created Sale object.
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