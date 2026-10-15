# MDS-API

Public API for [Modern Damage System](https://github.com/JuzipiAN/modern-damage-system).
It lets other mods read and change MDS damage, enchantment, config and HUD data, and it keeps
working when Modern Damage System itself is not installed.

Both loader builds live in this repo:

```
fabric/    Fabric build of the API. 7 classes under com/juzipi/criticalhits/api,
           plus the mod metadata.
neoforge/  NeoForge build of the API. A complete Gradle project you can build on its own.
```

## Versions

| Loader | Version | Minecraft | Jar |
|---|---|---|---|
| Fabric | 1.1.0 | 26.2 | `mds-api-1.1.0-fabric-26.2.jar` |
| NeoForge | 1.1.0 | 26.2 | `mds-api-neoforge-26.2-1.1.0.jar` |

Both jars are attached to the [releases](../../releases) page, together with their sources jars.

## What's in the API

| Class | Purpose |
|---|---|
| `MdsApi` | Entry point. Reports whether MDS is present and hands out the other facades. |
| `MdsDamageApi` | Damage numbers, crit rolls, armor penetration, and the combat hooks. |
| `MdsEnchantmentApi` | Enchantment ids, levels, and the per-level scaling values. |
| `MdsConfigApi` | Read and write MDS config values at runtime. |
| `MdsGuiApi` | HUD data other mods can display or extend. |
| `MdsApiClient` | Client-side helpers. Safe to load on a server. |
| `MdsApiMain` | Loader bootstrap. |

Everything degrades gracefully: if Modern Damage System isn't installed, the API returns its
documented fallback values instead of throwing.

## Building the NeoForge build

```bash
cd neoforge
./gradlew build
```

Output lands in `neoforge/build/libs`. Requires Java 25.

The Fabric build is currently produced by a `buildApiJar` task inside the main mod's Fabric
project, so `fabric/` here holds the sources and metadata rather than a standalone Gradle setup.

## Using it

Add the jar to your development dependencies, or drop it in `mods` alongside Modern Damage System.
Depend on mod id `mds_api` and check `MdsApi.isLoaded()` before calling into the facades.

## License

MIT. See [LICENSE](LICENSE). Use it, fork it, ship it inside your own mods.

The Modern Damage System mod itself is a separate project and keeps its own source-available
license, which is stricter. That license does not apply to this API.
