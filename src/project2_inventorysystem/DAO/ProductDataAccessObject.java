/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project2_inventorysystem.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author user
 */
public class ProductDataAccessObject {
  String sql;
  Connection conn;
  PreparedStatement pstmt;
  
  public ProductDataAccessObject(Connection conn){
    this.conn = conn;
  }
  
  public ResultSet getProducts(boolean isActive){
    try{
      sql = """
        SELECT 
            p.productID as ID,
            p.productName,
            p.categoryName as category,
            p.unitPrice,
            c.unit,
            p.stockQuantity as stock,
            p.lowStockthreshold as "lowStock threshold",
            DATE_FORMAT(p.dateCreated,"%Y-%d-%m") as dateCreated
        FROM tbl_products as p
        inner join tbl_categories as c 
            on  p.categoryName = c.categoryName
        WHERE p.isActive = ?;
      """;
      
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, ((isActive)? 1:0) );
      
      return pstmt.executeQuery();
    }catch(SQLException ex){
      ex.printStackTrace();
      return null;
    }
  }
  
  public ResultSet getLowStockProducts(){
    try{
      sql = """
        SELECT 
            p.productID,
            p.productName as name,
            IF(p.stockQuantity = 0,'OUT OF STOCK',p.stockQuantity) as stocks 
        FROM tbl_products as p 

        WHERE p.stockQuantity <= p.lowStockthreshold
            AND P.isActive = 1;
       """;

      pstmt = conn.prepareStatement(sql);
      return pstmt.executeQuery();
    }catch(SQLException ex){
      ex.printStackTrace();
      return null;
    }
  }
  
  public ResultSet getBestSellerProducts(){
    try{
      sql = """
        SELECT 
          P.productID,
          P.productName       AS name,
          P.unitPrice         AS price,
          P.categoryName      AS category,
          S.total_sale
        FROM tbl_products AS P
        
        INNER JOIN (
        
          SELECT
              productID,
              SUM(quantity) AS total_sale
          FROM tbl_sales 
            
          GROUP BY productID
        ) AS S ON P.productID = S.productID
        
        WHERE S.total_sale = (
          SELECT MAX(sales.total_sale)
          FROM tbl_products AS product

          INNER JOIN (
            SELECT
              productID,
              SUM(quantity) AS total_sale
            FROM tbl_sales
            GROUP BY productID
          ) AS sales ON product.productID = sales.productID

          WHERE product.categoryName = P.categoryName
           AND P.isActive = 1
        )
            
        ORDER BY total_sale DESC;
      """;

      pstmt = conn.prepareStatement(sql);
      return pstmt.executeQuery();
    }catch(SQLException ex){
      ex.printStackTrace();
      return null;
    }
  }
  
  public ResultSet getProductCategories(){
    try{
      sql = """
          SELECT 
            categoryName
          FROM tbl_categories
          WHERE isActive = 1;
        """;
      
      pstmt = conn.prepareStatement(sql);
      return pstmt.executeQuery();
    }catch(SQLException ex){
      ex.printStackTrace();
      return null;
    }
    
  }
  
  public int setProductStatus(int productID, boolean isActive){
   try{
     sql = """
        UPDATE tbl_products
        SET
          isActive = ?
        WHERE productID = ?;
      """;
     
     pstmt = conn.prepareStatement(sql);
     pstmt.setInt(1, ((isActive)? 1:0) );
     pstmt.setInt(2, productID );
     
     return pstmt.executeUpdate();
   } catch(SQLException ex){
     ex.printStackTrace();
     return 0;
   }
  }
}
