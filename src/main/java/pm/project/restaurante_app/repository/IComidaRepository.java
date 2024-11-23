package pm.project.restaurante_app.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pm.project.restaurante_app.entity.Comida;

public interface IComidaRepository extends CrudRepository<Comida, Integer> {
    @Query("SELECT P FROM Comida P WHERE P.recomendado = true")
    Iterable<Comida> listarPlatillosRecomendados();

    @Query("SELECT P FROM Comida P WHERE P.categoria.id=:idC")
    Iterable<Comida> listarPlatillosPorCategoria(int idC);

//    @Modifying
//    @Query("UPDATE Comida C SET C.stock= C.stock - :cant WHERE C.id=:id")
//    void descontarStock(int cant, int id);
//
//    @Modifying
//    @Query("UPDATE Comida C SET C.stock= C.stock + :cant WHERE C.id=:id")
//    void aumentarStock(int cant, int id);

    @Modifying
    @Query("UPDATE Comida C SET C.stock= C.stock - :cant WHERE C.id=:id")
    void actualizarStock(int cant, int id);

}