package com.example.indovinasomma

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var saldo = 1000
    var puntata = 0
    var somma = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        stringSaldo.text = String.format(resources.getString(R.string.saldo),saldo)
        stringPuntata.text = String.format(resources.getString(R.string.puntata),0)
    }
    fun startGame(v: View) {
        val puntataScelta = inputPuntata.text.toString().toInt()
        val estrazione1 = (0..5).random()
        val estrazione2 = (0..5).random()
        val facce = arrayOf(R.drawable.faccia_dado1,R.drawable.faccia_dado2,R.drawable.faccia_dado3,R.drawable.faccia_dado4,R.drawable.faccia_dado5,R.drawable.faccia_dado6)
        puntata = puntataScelta
        stringPuntata.text = String.format(resources.getString(R.string.puntata),puntata)
        cambiaStatoPuntataTentativo(false,true)
        somma = (estrazione1+1)+(estrazione2+1)
        buttonProva.setOnClickListener {
            val sommaInserita = provaSomma.text.toString().toInt()
            if (sommaInserita == somma) {
                saldo = saldo+puntata
                risultato.text = "BRAVO!!! La somma era $somma. Guadagni $puntata punti."
            } else {
                saldo = saldo-puntata
                risultato.text = "HAI SBAGLIATO!!! La somma era $somma. Perdi $puntata punti"
            }
            facciaDado1.setImageResource(facce[estrazione1])
            facciaDado2.setImageResource(facce[estrazione2])
            buttonContinue.isVisible = true
            stringSaldo.text = String.format(resources.getString(R.string.saldo),saldo)
            stringPuntata.text = String.format(resources.getString(R.string.puntata), 0)
        }
    }
    fun continueGame(v: View) {
        cambiaStatoPuntataTentativo(true,false)
        somma = 0
        v.isVisible = false
        facciaDado1.setImageResource(R.drawable.faccia_dado_start)
        facciaDado2.setImageResource(R.drawable.faccia_dado_start)
        risultato.text = resources.getString(R.string.resultString)
        inputPuntata.text.clear()
        provaSomma.text.clear()
    }
    fun cambiaStatoPuntataTentativo(statoPuntata: Boolean, statoTentativo: Boolean) {
        inputPuntata.isVisible = statoPuntata
        buttonPunta.isVisible = statoPuntata
        provaSomma.isVisible = statoTentativo
        buttonProva.isVisible = statoTentativo
    }
}