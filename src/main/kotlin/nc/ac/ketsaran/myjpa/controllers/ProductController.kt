package nc.ac.ketsaran.myjpa.controllers

import nc.ac.ketsaran.myjpa.models.Products
import nc.ac.ketsaran.myjpa.repository.ProductRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
// this controller connect to product repository (in java we called autowired)
class ProductController(private val productRepository: ProductRepository) {
    // like select * from table.product
    @GetMapping
    fun getAllProducts(): List<Products> = productRepository.findAll()
    // like select id from table.product
    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ResponseEntity<Products> =
        productRepository.findById(id)
            .map { ResponseEntity(it, HttpStatus.OK) }
            .orElse(ResponseEntity(HttpStatus.NOT_FOUND))
    // like insert into product
    @PostMapping
    fun createProduct(@RequestBody product: Products): Products = productRepository.save(product)
    // like update table
    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody product: Products): ResponseEntity<Products> =
        productRepository.findById(id)
            .map {
                val updatedProduct = it.copy(
                    name = product.name,
                    price = product.price,
                    description = product.description,
                    unitInStock = product.unitInStock
                )
                ResponseEntity(productRepository.save(updatedProduct), HttpStatus.OK)
            }
            .orElse(ResponseEntity(HttpStatus.NOT_FOUND))

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Void> =
        productRepository.findById(id)
            .map {
                productRepository.delete(it)
                ResponseEntity<Void>(HttpStatus.NO_CONTENT)
            }
            .orElse(ResponseEntity(HttpStatus.NOT_FOUND))

    @GetMapping("/name/{name}")
    fun getByName(@PathVariable name:String): List<Products> = productRepository.findByName(name);

    @GetMapping("/price/{price}")
    fun getProductByPrice(@PathVariable price:Double): List<Products> = productRepository.findByPriceGreaterThanEqual(price);

    @GetMapping("/unitInStock/{unitInStock}")
    fun getProductByUnitInStock(@PathVariable unitInStock: Int): List<Products> = productRepository.findByUnitInStockLessThanEqual(unitInStock);

    @GetMapping("/description/{description}")
    fun getProductByDescription(@PathVariable description: String): List<Products> = productRepository.findByDescriptionContaining(description);
}