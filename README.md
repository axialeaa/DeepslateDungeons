![Deepslate stronghold library, watermark saying "Alexia"](https://raw.githubusercontent.com/axialeaa/Axialeaa-BrandingAssets/refs/heads/main/MODS/DeepslateDungeons/cover.png)

<p align=center>
  <!-- FAPI -->
  <a href=https://modrinth.com/mod/fabric-api>
    <img alt="fabric-api" height="64" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/requires/fabric-api_vector.svg">
  </a>

  <!-- GitHub -->
  <a href=https://github.com/axialeaa/DeepslateDungeons>
    <img alt="github" height="64" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/available/github_vector.svg">
  </a>

  <!-- Gallery -->
  <a href=https://modrinth.com/mod/deepslate-dungeons/gallery>
    <img alt="modrinth-gallery" height="64" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/documentation/modrinth-gallery_vector.svg">
  </a>

  <!-- Ko-Fi -->
  <a href=https://ko-fi.com/axialeaa>
      <img alt="kofi-singular" height="64" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy-minimal/donate/kofi-singular_vector.svg">
  </a>

  <!-- Discord -->
  <a href=https://discord.gg/hfVmpeQhe8>
      <img alt="discord-singular" height="64" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy-minimal/social/discord-singular_vector.svg">
  </a>
</p>

<p align=center>
    <b>Deepslate Dungeons</b> improves the environmental storytelling of the game by switching up the block palettes of all dungeon/stronghold rooms below y = 0 for deepslate variants! This also helps to reduce thematic clashing when you're deep underground.
</p>

<h2><p align=center>🎨 Customization</p></h2>

There are a number of gamerules for you to use to make your playthroughs just as you'd like them to be! They each must be prefixed with the namespace `deepslate-dungeons` in commands.

|        Registry Identifier        | Description                                                  | Default Value | Value Bounds |
|:---------------------------------:|:-------------------------------------------------------------|:-------------:|:------------:|
|     `stronghold_y_threshold`      | The max Y level deep stronghold room variants generate at.   |       0       |    -∞...∞    |                                                 
|   `dungeon_origin_y_threshold`    | The max Y level deep dungeon variants generate at.           |       0       |    -∞...∞    |                                                                  
|     `stronghold_convert_oak`      | Whether deep stronghold variants replace oak with dark oak.  |     true      | true, false  |
|   `stronghold_convert_torches`    | Whether deep stronghold variants include soul torches.       |     false     | true, false  |
| `chiseled_bookshelves_in_library` | Whether stronghold libraries generate chiseled bookshelves.* |     false     | true, false  |

##### * Some books will be enchanted. This feature is a little experimental and may get moved to a separate mod at some point! Check out the gallery for an example of how this looks in-game.
