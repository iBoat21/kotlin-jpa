package nc.ac.ketsaran.myjpa.repository

import nc.ac.ketsaran.myjpa.models.Products
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository: JpaRepository<Products, Long> {

    fun findByName(name: String): List<Products>

    fun findByPriceGreaterThanEqual(price: Double): List<Products>

    fun findByUnitInStockLessThanEqual(unitInStock: Int): List<Products>

    fun findByDescriptionContaining(description: String): List<Products>
}