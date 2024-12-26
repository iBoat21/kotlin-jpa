package nc.ac.ketsaran.myjpa.models

import jakarta.persistence.*

@Entity
data class Products(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val price: Double,
    val description: String,
    val unitInStock: Int
)