package dk.itu.shoppingcompose

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemList(
    list: List<Item>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier.padding(bottom = 20.dp)) {
        items(items = list) { item ->
            ItemLine(item.what, item.where)
        }
    }
}

@Composable
private fun ItemLine(
    what: String,
    where: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
            text = "Buy $what in: $where", fontSize = 20.sp
        )
    }
}