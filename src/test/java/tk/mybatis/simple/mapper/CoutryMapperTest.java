package tk.mybatis.simple.mapper;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;

import tk.mybatis.simple.model.Country;
import tk.mybatis.simple.model.CountryExample;

public class CoutryMapperTest extends BaseMapperTest {

	@Test
	public void testExample() {
		// get SqlSession
		SqlSession slqSession = getSqlSession();
		try {
			// get countryMapper interface object
			CountryMapper countryMapper = slqSession.getMapper(CountryMapper.class);

			// create object of CountryExample
			CountryExample example = new CountryExample();

			// set order by
			example.setOrderByClause("id desc, countryname desc");
			example.setDistinct(true);
			// create search criteria
			CountryExample.Criteria criteria = example.createCriteria();
			// id >= 1
			criteria.andIdGreaterThanOrEqualTo(1);
			// id < 4
			criteria.andIdLessThan(4);
			// ATTENTION: you must provide the '%' character here
			criteria.andCountrycodeLike("%U%");
			// or
			CountryExample.Criteria or = example.or();
			or.andCountrynameEqualTo("中国");

			// execute the search
			List<Country> countryList = countryMapper.selectByExample(example);
			Assert.assertNotNull(countryList);
			// printCountryList(countryList);
		} finally {
			// Please don't forget to close the session after each test case
			slqSession.close();
		}
	}

	@Test
	public void testUpdateByExampleSelective() {
		// get SqlSession
		SqlSession slqSession = getSqlSession();
		try {
			// get countryMapper interface object
			CountryMapper countryMapper = slqSession.getMapper(CountryMapper.class);
			// create example object
			CountryExample example = new CountryExample();
			// create criteria
			CountryExample.Criteria criteria = example.createCriteria();
			criteria.andIdGreaterThan(2);
			
			// create a new object and set the country name to 'China'
			Country country = new Country();
			country.setCountryname("China");
			
			// do search and update
			countryMapper.updateByExampleSelective(country, example);
			// output the update result
			// printCountryList(countryMapper.selectByExample(example));
			
		} finally {
			// Do really update the database, just rollback
			slqSession.rollback();
			// Please don't forget to close the session after each test case
			slqSession.close();
		}
	}
	//@Test
	public void testDeleteByExample() {
		// get SqlSession
		SqlSession slqSession = getSqlSession();
		try {
			// get countryMapper interface object
			CountryMapper countryMapper = slqSession.getMapper(CountryMapper.class);
			
			CountryExample example = new CountryExample();
			CountryExample.Criteria criteria = example.createCriteria();
			criteria.andIdGreaterThan(2);
			countryMapper.deleteByExample(example);
			// because all records greater than 2 was already deleted, so here it should
			// cannot find any records
			Assert.assertEquals(0, countryMapper.countByExample(example));
			
		} finally {
			// Do really update the database, just rollback
			slqSession.rollback();
			// Please don't forget to close the session after each test case
			slqSession.close();
		}
	}
	
	/**
	 * output helper method
	 * @param countryList
	 */
	public void printCountryList(List<Country> countryList) {
		for (Country country : countryList) {
			System.out.println(country.toString());
		}
	}
}
