package dk.itu.shoppingcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dk.itu.shoppingcompose.ui.theme.ShoppingComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingScreen()
        }
    }
}

@Composable
private fun ShoppingScreen(itemsDB: ItemsViewModel = viewModel()) {
    Column {
        AddItem(itemsDB, modifier = Modifier.padding(start = 5.dp, top = 10.dp, end = 5.dp))

        Spacer(modifier = Modifier.height(20.dp))

        ItemList(list = itemsDB.listItems())
    }
}

@Composable
private fun AddItem(itemsDB: ItemsViewModel, modifier: Modifier = Modifier) {
    var where: String by remember { mutableStateOf("") }
    var what: String by remember { mutableStateOf("") }

    Row(modifier = modifier.fillMaxWidth()) {
        val focusManager = LocalFocusManager.current

        OutlinedTextField(
            value = what,
            onValueChange = { what = it },
            label = { Text("What", fontSize = 20.sp) },
            modifier = Modifier.weight(1f),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions { focusManager.moveFocus(FocusDirection.Next) },
        )

        Spacer(modifier = Modifier.width(8.dp))

        OutlinedTextField(
            value = where,
            onValueChange = { where = it },
            label = { Text("Where", fontSize = 20.sp) },
            modifier = Modifier.weight(1f),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions { focusManager.clearFocus() }
        )
    }

    AddButton(itemsDB, what, where)
}

@Composable
private fun AddButton(itemsDB: ItemsViewModel, what: String, where: String) {
    Button(
        onClick = { itemsDB.addItem(what, where) },
        modifier = Modifier.padding(all = 8.dp),
        enabled = true,
        border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Blue)),
        shape = MaterialTheme.shapes.medium,
    ) {
        Text(text = "Add Item", color = Color.White, fontSize = 20.sp)
    }
}

@Preview(showBackground = true)
@Composable
private fun ShoppingScreenPreview() {
    ShoppingComposeTheme {
        ShoppingScreen()
    }
}