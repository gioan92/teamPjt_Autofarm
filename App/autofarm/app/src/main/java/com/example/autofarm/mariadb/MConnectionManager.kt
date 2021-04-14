//package mariadb
//
//import java.sql.DriverManager
//import java.sql.ResultSet
//
//class MConnectionManager {
//    companion object {
//        fun <T> executeSql(url: String, user: String, pw: String, query: String, factory: (rs: ResultSet) -> T, callback: (items: MutableList<T>?, msg: String) -> Unit)
//                = CoroutineScope(Dispatchers.Default).launch {
//                    try {
//                        Class.forName("org.mariadb.jdbc.Driver")
//                        DriverManager.setLoginTimeout(1)
//                        val conn = DriverManager.getConnection(url, user, pw)
//                        val state = conn.createStatement()
//
//                        val rs = state.executeQuery(query)
//
//                        val result = mutableListOf<T>()
//                        while (rs.next()) {
//                            result.add(factory.invoke(rs))
//                        }
//                        withContext(Dispatchers.Main) {
//                            callback.invoke(result, "")
//                        }
//                    } catch (e: Exception) {
//                        withContext(Dispatchers.Main) {
//                            callback.invoke(null, e.message ?: "알수 없는 오류입니다.")
//                        }
//                    }
//            }
//    }
//}