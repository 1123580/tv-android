package top.yogiczy.mytv.tv.ui.screen.settings.subcategories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Icon
import androidx.tv.material3.ListItem
import androidx.tv.material3.ListItemDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import top.yogiczy.mytv.tv.ui.rememberChildPadding
import top.yogiczy.mytv.tv.ui.screen.components.AppScreen
import top.yogiczy.mytv.tv.ui.theme.MyTvTheme
import top.yogiczy.mytv.tv.ui.utils.Configs
import top.yogiczy.mytv.tv.ui.utils.handleKeyEvents

@Composable
fun SettingsVideoPlayerCoreScreen(
    modifier: Modifier = Modifier,
    coreProvider: () -> Configs.VideoPlayerCore = { Configs.VideoPlayerCore.MEDIA3 },
    onCoreChanged: (Configs.VideoPlayerCore) -> Unit = {},
    onBackPressed: () -> Unit = {},
) {
    val childPadding = rememberChildPadding()

    AppScreen(
        modifier = modifier.padding(top = 10.dp),
        header = { Text("设置 / 播放器 / 内核") },
        canBack = true,
        onBackPressed = onBackPressed,
    ) {
        LazyColumn(
            contentPadding = childPadding.copy(top = 10.dp).paddingValues,
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(Configs.VideoPlayerCore.entries) { core ->
                ListItem(
                    modifier = Modifier.handleKeyEvents(
                        onSelect = { onCoreChanged(core) },
                    ),
                    headlineContent = { Text(core.label) },
                    supportingContent = {
                        Text(
                            when (core) {
                                Configs.VideoPlayerCore.MEDIA3 -> "支持全部功能"
                                Configs.VideoPlayerCore.IJK -> "部分功能可能无法正常使用"
                            }
                        )
                    },
                    trailingContent = {
                        if (coreProvider() == core) {
                            Icon(Icons.Default.CheckCircle, contentDescription = null)
                        }
                    },
                    colors = ListItemDefaults.colors(
                        containerColor = MaterialTheme.colorScheme.onSurface.copy(0.1f),
                    ),
                    selected = false,
                    onClick = {},
                )
            }
        }
    }
}

@Preview(device = "id:Android TV (720p)")
@Composable
private fun SettingsVideoPlayerCoreScreenPreview() {
    MyTvTheme {
        SettingsVideoPlayerCoreScreen()
    }
}