package com.yoox.items.utilities

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Night mode - standard screens",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true,
    locale = "en"
)
annotation class NightModeStandardScreensEn

@Preview(
    name = "Light mode - standard screens",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true,
    locale = "en"
)
annotation class LightModeStandardScreensEn

@Preview(
    name = "Night mode - standard screens",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true,
    locale = "it"
)
annotation class NightModeStandardScreensIt

@Preview(
    name = "Light mode - standard screens",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true,
    locale = "it"
)
annotation class LightModeStandardScreensIt

@Preview(
    name = "Light mode - compact screens",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true,
    locale = "en",
    device = "spec:parent=pixel"
)
annotation class LightModeCompactScreensEn

@Preview(
    name = "Dark mode - compact screens",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true,
    locale = "en",
    device = "spec:parent=pixel"
)
annotation class DarkModeCompactScreensEn

@Preview(
    name = "Light mode - compact screens",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    showSystemUi = true,
    locale = "it",
    device = "spec:parent=pixel"
)
annotation class LightModeCompactScreensIt

@Preview(
    name = "Dark mode - compact screens",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    showSystemUi = true,
    locale = "it",
    device = "spec:parent=pixel"
)
annotation class DarkModeCompactScreensIt
