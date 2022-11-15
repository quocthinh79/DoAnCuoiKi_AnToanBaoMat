package Dao;

import Beans.Brand;
import Beans.Color;
import Beans.Image;
import Beans.Product;
import Beans.ProductNew;
import Beans.Size;
import Beans.Tag;
import api.object_reponse.InfoDelete;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.Object;

public class ProductDao {
    public static List<Product> loadProduct() {
        List<Product> products = new ArrayList<>();
        Connection connection = Connect.getInstance().getConnection();
        try {
            String query = "CALL layDanhSachSanPham()";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String brand = resultSet.getString(3);
                String price = resultSet.getString(4);
                String description = resultSet.getString(5);
                String thumbnail = resultSet.getString(6);
                Timestamp date = resultSet.getTimestamp(7);
                int numberOfRate = resultSet.getInt(8);
                int rate = resultSet.getInt(9);
                int number = resultSet.getInt(10);
                product.setId(id);
                product.setName(name);
                product.setBrand(brand);
                product.setPrice(price);
                product.setDescription(description);
                product.setThumbnail(thumbnail);
                product.setDate(date);
                product.setNumberOfRate(numberOfRate);
                product.setRate(rate);
                product.setNumber(number);
                products.add(product);
                getColorsForProduct(product);
                getSizesForProduct(product);
                getTagsForProduct(product);
                getImagesForProduct(product);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

	// public static int countProduct() {
//        int result = 0;
//        Connection connection = Connect.getInstance().getConnection();
//        try {
//            String query = "SELECT COUNT(?) FROM SANPHAM";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, "MA_SP");
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                result = resultSet.getInt(1);
//            }
//            resultSet.close();
//            preparedStatement.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
	public static int countProduct(String type, String filterBrand, String color) {
		int size = 0;
		Connection connection = Connect.getInstance().getConnection();
		try {
			String query = "SELECT COUNT(sp.MA_SP) FROM SANPHAM AS sp, NHANHIEU AS nh";
			if (!type.equals("all") && !filterBrand.equals("all") && !color.equals("all")) {
				query += ", THE AS th, MAU AS m WHERE sp.MA_NHAN_HIEU = nh.MA_NHAN_HIEU AND sp.MA_SP = th.MA_SP AND sp.MA_SP = m.MA_SP AND nh.MA_NHAN_HIEU = ? AND th.MA_CT_THE = ? AND m.MA_CT_MAU = ?";
			} else if (type.equals("all") && !filterBrand.equals("all") && !color.equals("all")) {
				query += ",MAU AS m WHERE sp.MA_NHAN_HIEU = nh.MA_NHAN_HIEU AND sp.MA_SP = m.MA_SP AND nh.MA_NHAN_HIEU = ? AND m.MA_CT_MAU = ?";
			} else if (!type.equals("all") && filterBrand.equals("all") && !color.equals("all")) {
				query += ",THE AS th, MAU AS m WHERE sp.MA_NHAN_HIEU = nh.MA_NHAN_HIEU AND sp.MA_SP = th.MA_SP AND sp.MA_SP = m.MA_SP AND th.MA_CT_THE = ? AND m.MA_CT_MAU = ?";
			} else if (!type.equals("all") && !filterBrand.equals("all") && color.equals("all")) {
				query += ",THE AS th WHERE sp.MA_NHAN_HIEU = nh.MA_NHAN_HIEU AND sp.MA_SP = th.MA_SP AND nh.MA_NHAN_HIEU = ? AND th.MA_CT_THE = ?";
			} else if (type.equals("all") && filterBrand.equals("all") && !color.equals("all")) {
				query += ",MAU AS m WHERE sp.MA_NHAN_HIEU = nh.MA_NHAN_HIEU AND sp.MA_SP = m.MA_SP AND m.MA_CT_MAU = ?";
			} else if (!type.equals("all") && filterBrand.equals("all") && color.equals("all")) {
				query += ",THE AS th WHERE sp.MA_NHAN_HIEU = nh.MA_NHAN_HIEU AND sp.MA_SP = th.MA_SP AND th.MA_CT_THE = ?";
			} else if (type.equals("all") && !filterBrand.equals("all") && color.equals("all")) {
				query += " WHERE sp.MA_NHAN_HIEU = nh.MA_NHAN_HIEU AND nh.MA_NHAN_HIEU = ?";
			} else {
				query += " WHERE sp.MA_NHAN_HIEU = nh.MA_NHAN_HIEU";
			}
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			if (!type.equals("all") && !filterBrand.equals("all") && !color.equals("all")) {
				preparedStatement.setString(1, filterBrand);
				preparedStatement.setString(2, type);
				preparedStatement.setString(3, color);
			} else if (type.equals("all") && !filterBrand.equals("all") && !color.equals("all")) {
				preparedStatement.setString(1, filterBrand);
				preparedStatement.setString(2, color);
			} else if (!type.equals("all") && filterBrand.equals("all") && !color.equals("all")) {
				preparedStatement.setString(1, type);
				preparedStatement.setString(2, color);
			} else if (!type.equals("all") && !filterBrand.equals("all") && color.equals("all")) {
				preparedStatement.setString(1, filterBrand);
				preparedStatement.setString(2, type);
			} else if (type.equals("all") && filterBrand.equals("all") && !color.equals("all")) {
				preparedStatement.setString(1, color);
			} else if (!type.equals("all") && filterBrand.equals("all") && color.equals("all")) {
				preparedStatement.setString(1, type);
			} else if (type.equals("all") && !filterBrand.equals("all") && color.equals("all")) {
				preparedStatement.setString(1, filterBrand);
			}
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				size = resultSet.getInt(1);
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return size;
	}

	public static List<Product> loadProduct(int begin, int numOfProduct, String type, String filterBrand, String color,
			String orderBy) {
		List<Product> products = new ArrayList<>();
		Connection connection = Connect.getInstance().getConnection();
		try {
			String query = "SELECT sp.MA_SP, sp.TEN_SP, nh.TEN_NHAN_HIEU, sp.GIA, sp.MO_TA, sp.HINH_NHO, sp.NGAY_THEM,  sp.TONG_DANH_GIA, sp.TONG_SAO, sp.SO_LUONG FROM SANPHAM AS sp, NHANHIEU AS nh";
			if (!type.equals("all") && !filterBrand.equals("all") && !color.equals("all")) {
				query += ", THE AS th, MAU AS m WHERE sp.MA_NHAN_HIEU = nh.MA_NHAN_HIEU AND sp.MA_SP = th.MA_SP AND sp.MA_SP = m.MA_SP AND nh.MA_NHAN_HIEU = ? AND th.MA_CT_THE = ? AND m.MA_CT_MAU = ?";
			} else if (type.equals("all") && !filterBrand.equals("all") && !color.equals("all")) {
				query += ",MAU AS m WHERE sp.MA_NHAN_HIEU = nh.MA_NHAN_HIEU AND sp.MA_SP = m.MA_SP AND nh.MA_NHAN_HIEU = ? AND m.MA_CT_MAU = ?";
			} else if (!type.equals("all") && filterBrand.equals("all") && !color.equals("all")) {
				query += ",THE AS th, MAU AS m WHERE sp.MA_NHAN_HIEU = nh.MA_NHAN_HIEU AND sp.MA_SP = th.MA_SP AND sp.MA_SP = m.MA_SP AND th.MA_CT_THE = ? AND m.MA_CT_MAU = ?";
			} else if (!type.equals("all") && !filterBrand.equals("all") && color.equals("all")) {
				query += ",THE AS th WHERE sp.MA_NHAN_HIEU = nh.MA_NHAN_HIEU AND sp.MA_SP = th.MA_SP AND nh.MA_NHAN_HIEU = ? AND th.MA_CT_THE = ?";
			} else if (type.equals("all") && filterBrand.equals("all") && !color.equals("all")) {
				query += ",MAU AS m WHERE sp.MA_NHAN_HIEU = nh.MA_NHAN_HIEU AND sp.MA_SP = m.MA_SP AND m.MA_CT_MAU = ?";
			} else if (!type.equals("all") && filterBrand.equals("all") && color.equals("all")) {
				query += ",THE AS th WHERE sp.MA_NHAN_HIEU = nh.MA_NHAN_HIEU AND sp.MA_SP = th.MA_SP AND th.MA_CT_THE = ?";
			} else if (type.equals("all") && !filterBrand.equals("all") && color.equals("all")) {
				query += " WHERE sp.MA_NHAN_HIEU = nh.MA_NHAN_HIEU AND nh.MA_NHAN_HIEU = ?";
			} else {
				query += " WHERE sp.MA_NHAN_HIEU = nh.MA_NHAN_HIEU";
			}
			switch (orderBy) {
			case "desc":
				query += " ORDER BY sp.GIA DESC LIMIT ?, ?;";
				break;
			case "asc":
				query += " ORDER BY sp.GIA ASC LIMIT ?, ?;";
				break;
			default:
				query += " ORDER BY sp.NGAY_THEM DESC LIMIT ?, ?;";
				break;
			}
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			if (!type.equals("all") && !filterBrand.equals("all") && !color.equals("all")) {
				preparedStatement.setString(1, filterBrand);
				preparedStatement.setString(2, type);
				preparedStatement.setString(3, color);
				preparedStatement.setInt(4, begin);
				preparedStatement.setInt(5, numOfProduct);
			} else if (type.equals("all") && !filterBrand.equals("all") && !color.equals("all")) {
				preparedStatement.setString(1, filterBrand);
				preparedStatement.setString(2, color);
				preparedStatement.setInt(3, begin);
				preparedStatement.setInt(4, numOfProduct);
			} else if (!type.equals("all") && filterBrand.equals("all") && !color.equals("all")) {
				preparedStatement.setString(1, type);
				preparedStatement.setString(2, color);
				preparedStatement.setInt(3, begin);
				preparedStatement.setInt(4, numOfProduct);
			} else if (!type.equals("all") && !filterBrand.equals("all") && color.equals("all")) {
				preparedStatement.setString(1, filterBrand);
				preparedStatement.setString(2, type);
				preparedStatement.setInt(3, begin);
				preparedStatement.setInt(4, numOfProduct);
			} else if (type.equals("all") && filterBrand.equals("all") && !color.equals("all")) {
				preparedStatement.setString(1, color);
				preparedStatement.setInt(2, begin);
				preparedStatement.setInt(3, numOfProduct);
			} else if (!type.equals("all") && filterBrand.equals("all") && color.equals("all")) {
				preparedStatement.setString(1, type);
				preparedStatement.setInt(2, begin);
				preparedStatement.setInt(3, numOfProduct);
			} else if (type.equals("all") && !filterBrand.equals("all") && color.equals("all")) {
				preparedStatement.setString(1, filterBrand);
				preparedStatement.setInt(2, begin);
				preparedStatement.setInt(3, numOfProduct);
			} else {
				preparedStatement.setInt(1, begin);
				preparedStatement.setInt(2, numOfProduct);
			}
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();
				String id = resultSet.getString(1);
				String name = resultSet.getString(2);
				String brand = resultSet.getString(3);
				String price = resultSet.getString(4);
				String description = resultSet.getString(5);
				String thumbnail = resultSet.getString(6);
				Timestamp date = resultSet.getTimestamp(7);
				int numberOfRate = resultSet.getInt(8);
				int rate = resultSet.getInt(9);
				int number = resultSet.getInt(10);
				product.setId(id);
				product.setName(name);
				product.setBrand(brand);
				product.setPrice(price);
				product.setDescription(description);
				product.setThumbnail(thumbnail);
				product.setDate(date);
				product.setNumberOfRate(numberOfRate);
				product.setRate(rate);
				product.setNumber(number);
				products.add(product);
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

    public static Product getProduct(String productId) {
        Product product = new Product();
        Connection connection = Connect.getInstance().getConnection();
        try {
            String query = "CALL laySanPham(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String brand = resultSet.getString(3);
                String price = resultSet.getString(4);
                String description = resultSet.getString(5);
                String thumbnail = resultSet.getString(6);
                Timestamp date = resultSet.getTimestamp(7);
                int numberOfRate = resultSet.getInt(8);
                int rate = resultSet.getInt(9);
                int number = resultSet.getInt(10);
                product.setId(id);
                product.setName(name);
                product.setBrand(brand);
                product.setPrice(price);
                product.setDescription(description);
                product.setThumbnail(thumbnail);
                product.setDate(date);
                product.setNumberOfRate(numberOfRate);
                product.setRate(rate);
                product.setNumber(number);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getColorsForProduct(product);
        getSizesForProduct(product);
        getTagsForProduct(product);
        getImagesForProduct(product);
        return product;
    }

    public static void getColorsForProduct(Product product) {
        Connection connection = Connect.getInstance().getConnection();
        try {
            String query = "CALL layDSMau(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String color = resultSet.getString(1);
                product.addColor(color);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkColorForImage(String idProduct, String colorID) {
        Connection connection = Connect.getInstance().getConnection();
        List<String> result = new ArrayList<>();
        try {
            String query = "SELECT MA_SP FROM HINHANH WHERE MA_SP =? AND MA_CT_MAU =?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idProduct);
            preparedStatement.setString(2, colorID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String maSP = resultSet.getString(1);
                result.add(maSP);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result.size() == 0) {
            return false;
        }
        return true;
    }

    public static boolean checkColorForProduct(String idProduct, String colorID) {
        Connection connection = Connect.getInstance().getConnection();
        try {
            String query = "CALL layDSMau(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, idProduct);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String color = resultSet.getString(1);
                if (color.equals(colorID)) {
                    preparedStatement.close();
                    resultSet.close();
                    return true;
                }
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void getSizesForProduct(Product product) {
        Connection connection = Connect.getInstance().getConnection();
        try {
            String query = "CALL layDSKichThuoc(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String size = resultSet.getString(1);
                product.addSize(size);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getTagsForProduct(Product product) {
        Connection connection = Connect.getInstance().getConnection();
        try {
            String query = "CALL layDSThe(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String tag = resultSet.getString(1);
                product.addTag(tag);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getImagesForProduct(Product product) {
        Connection connection = Connect.getInstance().getConnection();
        try {
            String query = "CALL layDSHinh(?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String image = resultSet.getString(1);
                product.addUrlImage(image);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	public static List<Image> getAllImage() {
		Connection connection = Connect.getInstance().getConnection();
		List<Image> result = new ArrayList<>();
		try {
			String query = "SELECT h.MA_SP, ctm.TEN_MAU, h.DUONG_DAN_ANH FROM HINHANH AS h, CHITIETMAU AS ctm WHERE h.MA_CT_MAU = ctm.MA_CT_MAU";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Image img = new Image();
				String idProduct = resultSet.getString(1);
				String colorName = resultSet.getString(2);
				String pathImg = resultSet.getString(3);
				img.setIdProduct(idProduct);
				img.setColorName(colorName);
				img.setPathImg(pathImg);
				result.add(img);
			}
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

    public static boolean addImageDetail(String idProduct, String colorProduct, String pathImg) {
        String sql = "INSERT INTO HINHANH(MA_SP, MA_CT_MAU, DUONG_DAN_ANH) VALUES(?,?,?)";
        boolean result = false;
        Connection connection = Connect.getInstance().getConnection();
        try {
            String idColors[] = {colorProduct};
            if (!checkColorForProduct(idProduct, colorProduct)) {
                String subSql = "INSERT INTO MAU(MA_SP, MA_CT_MAU) VALUES(?,?)";
                PreparedStatement subPreparedStatement = connection.prepareStatement(subSql);
                subPreparedStatement.setString(1, idProduct);
                subPreparedStatement.setString(2, colorProduct);
                subPreparedStatement.executeUpdate();
            }
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, idProduct);
            preparedStatement.setString(2, colorProduct);
            preparedStatement.setString(3, pathImg);
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet > 0) {
                result = true;
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean deleteImageDetail(String idProduct, String pathImg, String idColor) {
        String sql = "DELETE FROM HINHANH WHERE DUONG_DAN_ANH =?";
        boolean result = false;
        Connection connection = Connect.getInstance().getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pathImg);
            int resultSet = preparedStatement.executeUpdate();
            if (resultSet > 0) {
                if (!checkColorForImage(idProduct, idColor)) {
                    String subSql = "DELETE FROM MAU WHERE MA_SP =? AND MA_CT_MAU =?";
                    PreparedStatement subPreparedStatement = connection.prepareStatement(subSql);
                    subPreparedStatement.setString(1, idProduct);
                    subPreparedStatement.setString(2, idColor);
                    int subResultSet = preparedStatement.executeUpdate();
                    if (subResultSet > 0) {
                        result = true;
                    }
                }
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean checkCodeProduct(String codeProduct) {
        String sql = "select exists(select s.ma_sp from sanpham s where s.ma_sp=?)";
        Connection connection = Connect.getInstance().getConnection();
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, codeProduct);
            resultSet = preparedStatement.executeQuery();
            int code = -1;
            while (resultSet.next()) {
                code = resultSet.getInt(1);
            }
            resultSet.close();
            preparedStatement.close();
            if (code == 1)
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(connection, resultSet, preparedStatement);
        }
    }

	public static String addProduct(String codeProduct, String nameProduct, String brand, double price_product,
			String description, String tag_product[], String color_product[], String size_product[], String thumbnail,
			String[] listImage, String creator) throws SQLException {
		String sql = "insert into sanpham (ma_sp, ten_sp, ma_nhan_hieu, gia, mo_ta, hinh_nho, nguoi_them) values (?, ?,?, ?, ?, ?, ?)";
		Connection connection = Connect.getInstance().getConnection();
		PreparedStatement preparedStatement = null;
		connection.setAutoCommit(false);
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, codeProduct);
			preparedStatement.setString(2, nameProduct);
			preparedStatement.setString(3, brand);
			preparedStatement.setDouble(4, price_product);
			preparedStatement.setString(5, description);
			preparedStatement.setString(6, thumbnail);
			preparedStatement.setString(7, creator);
			if (preparedStatement.executeUpdate() > 0 && addTagToProduct(codeProduct, tag_product, connection) == 1
					&& addColorProduct(codeProduct, color_product, connection) == 1
					&& addSizeProduct(codeProduct, size_product, connection) == 1
					&& addImagesProduct(codeProduct, color_product, listImage, connection) == 1) {
				connection.commit();
				return "Thêm sản phẩm thành công";
			}
			return "Xảy ra lỗi khi thêm sản phẩm";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Thêm không thành công vì đã xảy ra lỗi";
		} finally {
			close(preparedStatement, connection);
		}
	}

	// add tag cho sản phẩm được thêm
	public static int addTagToProduct(String codeProduct, String[] tagProducts, Connection connection)
			throws SQLException {
		String sql = "INSERT INTO THE(MA_SP, MA_CT_THE) VALUES (?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			for (String maCTThe : tagProducts) {
				preparedStatement.setString(1, codeProduct);
				preparedStatement.setString(2, maCTThe);
				preparedStatement.addBatch();
			}
			int[] rowEffects = preparedStatement.executeBatch();
			int complete = 1;// hoan thanh
			for (int effect : rowEffects) {
				if (effect <= 0) {
					complete = 0;// khong hoan thanh
					return complete;
				}
			}
			return complete;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close(preparedStatement);
		}
	}

	// add màu cho sản phẩm vừa được thêm vào
	public static int addColorProduct(String codeProduct, String[] colorProducts, Connection connection)
			throws SQLException {
		String sql = "INSERT INTO MAU(MA_SP, MA_CT_MAU) VALUES (?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		try {
			for (String maCTMau : colorProducts) {
				preparedStatement.setString(1, codeProduct);
				preparedStatement.setString(2, maCTMau);
				preparedStatement.addBatch();
			}
			int[] rowEffects = preparedStatement.executeBatch();
			int complete = 1;// hoan thanh
			for (int effect : rowEffects) {
				if (effect <= 0) {
					complete = 0;// khong hoan thanh
					return complete;
				}
			}
			return complete;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close(preparedStatement);
		}
	}

	// them hinh ảnh vào cho sản phẩm mới
	public static int addImagesProduct(String codeProduct, String[] colorProduct, String[] imagesPath,
			Connection connection) throws SQLException {
		String sql = "INSERT INTO HINHANH(MA_SP, MA_CT_MAU, DUONG_DAN_ANH) VALUES (?,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		try {
			for (String imagePath : imagesPath) {
				if (imagePath != null) {
					preparedStatement.setString(1, codeProduct);
					preparedStatement.setString(2, colorProduct[0]);
					preparedStatement.setString(3, imagePath);
					preparedStatement.addBatch();
				}
			}
			int[] rowEffects = preparedStatement.executeBatch();
			int complete = 1; // hoan thanh
			for (int effect : rowEffects) {
				if (effect <= 0) {
					complete = 0; // khong hoan thanh
					return complete;
				}
			}
			return complete;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close(preparedStatement);
		}
	}

	// thêm kích thước cho các sản phẩm
	public static int addSizeProduct(String codeProduct, String[] sizeProducts, Connection connection)
			throws SQLException {
		String sql = "INSERT INTO KICHTHUOC(MA_SP, MA_CT_KICH_THUOC) VALUES (?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		try {
			for (String maCTKichThuoc : sizeProducts) {
				preparedStatement.setString(1, codeProduct);
				preparedStatement.setString(2, maCTKichThuoc);
				preparedStatement.addBatch();
			}
			int[] rowEffects = preparedStatement.executeBatch();
			int complete = 1; // hoan thanh
			for (int effect : rowEffects) {
				if (effect <= 0) {
					complete = 0; // khong hoan thanh
					return complete;
				}
			}
			return complete;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close(preparedStatement);
		}
	}

	// delete product by code product
	public static InfoDelete deleteProduct(String codeProduct) {
		InfoDelete infoDelete = new InfoDelete();
		String sql = "DELETE FROM SANPHAM WHERE MA_SP = ?";
		PreparedStatement preparedStatement = null;
		PreparedStatement subStatement = null;
		Connection connection = Connect.getInstance().getConnection();
		ResultSet resultSet = null;
		try {
			connection.setAutoCommit(false);
			if (deleteImagesProduct(codeProduct, connection, infoDelete) == 1
					&& deleteColorProduct(codeProduct, connection) == 1
					&& deleteSizeProduct(codeProduct, connection) == 1
					&& deleteTagProduct(codeProduct, connection) == 1) {
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, codeProduct);
				String subSQL = "SELECT S.HINH_NHO FROM SANPHAM S WHERE S.MA_SP=?";// get thumbnail product before
				// delete on databse
				subStatement = connection.prepareStatement(subSQL);
				subStatement.setString(1, codeProduct);
				resultSet = subStatement.executeQuery();
				while (resultSet.next()) {
					infoDelete.setThumnail(resultSet.getString("HINH_NHO"));
				}
				int rowEffect = preparedStatement.executeUpdate();
				if (rowEffect > 0) {
					connection.commit();
					infoDelete.setMessage("Xóa sản phẩm thành công");
					return infoDelete;
				} else {
					infoDelete.setMessage("Xóa sản phẩm thất bại");
					return infoDelete;
				}
			}
			infoDelete.setMessage("Lỗi trong quá trình xóa sản phẩm");
			return infoDelete;
		} catch (SQLException e) {
			e.printStackTrace();
			infoDelete.setMessage("Lỗi thực thi");
			return infoDelete;
		} finally {
			close(preparedStatement, connection, subStatement, resultSet);
		}
	}

	// delete images detail of product
	public static int deleteImagesProduct(String codeProduct, Connection connection, InfoDelete infoDelete) {
		String sql = "DELETE FROM HINHANH WHERE MA_SP = ?";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		PreparedStatement subStatement = null;
		List<String> imagesPath = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, codeProduct);
			String subSQL = "SELECT H.DUONG_DAN_ANH FROM HINHANH H WHERE H.MA_SP= ?";// get images path before delete on
			// database
			subStatement = connection.prepareStatement(subSQL);
			subStatement.setString(1, codeProduct);
			resultSet = subStatement.executeQuery();
			imagesPath = new ArrayList<String>();// list contains images path product
			while (resultSet.next()) {
				imagesPath.add(resultSet.getString("DUONG_DAN_ANH"));
			}
			infoDelete.setImages(imagesPath);
			int rowEffects = preparedStatement.executeUpdate();// excute detelete images of product
			return (rowEffects > 0) ? 1 : 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close(preparedStatement, resultSet, subStatement);
		}
	}

	// delete tags of product
	public static int deleteTagProduct(String codeProduct, Connection connection) {
		String sql = "DELETE FROM THE WHERE MA_SP = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, codeProduct);
			int rowEffects = preparedStatement.executeUpdate();
			return (rowEffects > 0) ? 1 : 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close(preparedStatement);
		}
	}

	// delete sizes of product
	public static int deleteSizeProduct(String codeProduct, Connection connection) {
		String sql = "DELETE FROM KICHTHUOC WHERE MA_SP = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, codeProduct);
			int rowEffects = preparedStatement.executeUpdate();
			return (rowEffects > 0) ? 1 : 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close(preparedStatement);
		}
	}

    // get product edit
    public static ProductNew getProductEdit(String codeProduct) {
        ProductNew productNew = new ProductNew();
        List<Tag> tags = TagDao.getTagOfProduct(codeProduct);
        List<Color> colors = ColorDao.getColorOfProduct(codeProduct);
        List<Size> sizes = SizeDao.getColorOfProduct(codeProduct);
//		List<Image> images = ImagesDao.getImagesOfProduct(codeProduct);
        productNew.setColors(colors);
        productNew.setSizes(sizes);
        productNew.setTags(tags);
//		productNew.setUrlImages(images);
        String sql = "SELECT S.MA_SP, S.TEN_SP, S.MA_NHAN_HIEU, S.GIA, S.MO_TA, S.HINH_NHO FROM SANPHAM S WHERE S.MA_SP=?";
        Connection connection = Connect.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, codeProduct);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                productNew.setId(resultSet.getString("MA_SP"));
                productNew.setName(resultSet.getString("TEN_SP"));
                productNew.setBrand(resultSet.getString("MA_NHAN_HIEU"));
                productNew.setPrice(resultSet.getString("GIA"));
                productNew.setDescription(resultSet.getString("MO_TA"));
                productNew.setThumbnail(resultSet.getString("HINH_NHO"));
            }
            return productNew;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            close(preparedStatement, connection, resultSet);
        }
    }

	public static String updateProduct(Product product) {
		String sql = "UPDATE SANPHAM SET TEN_SP=?, MA_NHAN_HIEU=?, GIA=?, MO_TA=?, HINH_NHO=? WHERE MA_SP=?";
		PreparedStatement preparedStatement = null;
		Connection connection = Connect.getInstance().getConnection();
		try {
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, product.getName());
			preparedStatement.setString(2, product.getBrand());
			preparedStatement.setDouble(3, Double.parseDouble(product.getPrice()));
			preparedStatement.setString(4, product.getDescription());
			preparedStatement.setString(5, product.getThumbnail());
			preparedStatement.setString(6, product.getId());
			int rowEffects = preparedStatement.executeUpdate();
			if (rowEffects > 0) {
				connection.commit();
				return "Cập nhật thành công";
			}
			return "Cập nhật thất bại";
		} catch (SQLException e) {
			e.printStackTrace();
			return "Lỗi thực thi";
		} finally {
			close(connection, preparedStatement);
		}
	}
	// delete colors of product
	public static int deleteColorProduct(String codeProduct, Connection connection) {
		String sql = "DELETE FROM MAU WHERE ma_sp=?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, codeProduct);
			int rowEffects = preparedStatement.executeUpdate();
			return (rowEffects > 0) ? 1 : 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			close(preparedStatement);
		}
	}

	public static void updateThumbnail(String codeProduct, String newPath) {
		String sql = "UPDATE SANPHAM SET HINH_NHO=? WHERE MA_SP=?";
		PreparedStatement preparedStatement = null;
		Connection connection = Connect.getInstance().getConnection();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newPath);
			preparedStatement.setString(2, codeProduct);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(connection, preparedStatement);
		}
	}

	// method help close object in SQL
	public static void close(Object... objects) {
		Object[] listObject = objects;
		for (Object object : listObject) {
			try {
				if (object instanceof Connection && object != null) {
					((Connection) object).close();
				} else if (object instanceof PreparedStatement && object != null) {
					((PreparedStatement) object).close();
					;
				} else if (object instanceof ResultSet && object != null) {
					((ResultSet) object).close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
