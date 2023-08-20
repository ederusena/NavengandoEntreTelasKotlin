package br.com.fiap.navengandoentretelas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.navengandoentretelas.screens.LoginScreen
import br.com.fiap.navengandoentretelas.screens.MenuScreen
import br.com.fiap.navengandoentretelas.screens.PedidosScreen
import br.com.fiap.navengandoentretelas.screens.PerfilScreen
import br.com.fiap.navengandoentretelas.ui.theme.NavengandoEntreTelasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavengandoEntreTelasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ) {
                        composable(route = "login") { LoginScreen(navController)}
                        composable(route = "menu") { MenuScreen(navController)}
                        composable(
                            route = "pedidos?nPedidos={numero}",
                            arguments = listOf(navArgument(name = "numero") {
                                defaultValue = "0 Pedidos"
                            })
                        )
                        {
                            val number = it.arguments?.getString("numero")
                            PedidosScreen(navController, number!!)
                        }
                        composable(route = "perfil/{nome}") {
                            val nome = it.arguments?.getString("nome")
                            PerfilScreen(navController, nome!!) // double Bang
                        }
                    }
                }
            }
        }
    }
}
