# Half Heart Survival (1.20.1 Fabric)

서바이벌에서 어떤 대미지(몬스터 공격, 낙사, 화상, 익사 등)를 받아도
체력이 **반 칸(1 HP)** 밑으로는 떨어지지 않는 모드입니다.
이미 반 칸이면 그 이후 대미지는 아예 무시됩니다. (죽지 않음)

- 몬스터/동물 등 플레이어가 아닌 엔티티에는 영향을 주지 않습니다.
- 크리에이티브 모드는 원래 대미지를 안 받으므로 영향 없습니다.
- 체력 회복(자연 회복, 음식, 물약 등)은 그대로 정상 작동합니다.

## 빌드 방법 A — GitHub Actions로 빌드 (설치 없이, 추천)

로컬에 자바나 그레이들을 안 깔아도 됩니다. 깃헙 저장소에 이 파일들을 올리기만 하면
`.github/workflows/build.yml` 이 자동으로 빌드해줍니다.

1. GitHub에서 새 저장소(Repository)를 하나 만듭니다. (Public/Private 상관없음)
2. 이 zip 압축을 풀어서 안에 있는 파일/폴더 전부(`.github` 폴더 포함)를
   그 저장소에 업로드합니다.
   - 웹에서 하려면: 저장소 페이지 → "Add file" → "Upload files" 로 통째로 드래그
   - 또는 git 명령어로 push
3. 저장소의 **Actions** 탭으로 이동합니다. "Build Mod" 워크플로우가 자동으로 실행됩니다.
   (안 돌아가면 "Run workflow" 버튼을 눌러 수동 실행)
4. 실행이 끝나면(초록색 체크 표시) 해당 실행 결과 페이지 하단 **Artifacts** 에서
   `halfheart-mod` 를 다운로드하면 그 안에 완성된 `.jar` 파일이 들어있습니다.
5. 그 `.jar` 파일을 `.minecraft/mods` 폴더에 넣고, **Fabric API**도 같은 폴더에 넣어주세요.
   (Fabric API: https://modrinth.com/mod/fabric-api → 1.20.x용 다운로드)
6. Fabric Loader 1.20.1로 게임을 켜면 적용됩니다.

## 빌드 방법 B — 직접 컴퓨터에서 빌드

1. [Fabric 공식 예제 모드 템플릿](https://fabricmc.net/develop/template/)에서
   Minecraft 버전 `1.20.1`, Loader `Fabric`으로 설정하고 zip을 받아 압축을 풉니다.
   (이 템플릿에는 gradlew 실행 파일이 포함되어 있어서 바로 빌드가 가능합니다)
2. 템플릿 폴더 안의 아래 파일들을, 지금 드린 파일로 **덮어씁니다**.
   - `build.gradle`
   - `gradle.properties`
   - `settings.gradle` (없으면 추가)
   - `src/main/resources/fabric.mod.json`
3. 템플릿 안의 `src/main/java/...` 밑에 있는 기존 예제 자바 파일들은 지우고,
   지금 드린 `HalfHeartMod.java` 파일을 `src/main/java/com/example/halfheart/` 경로에 넣습니다.
4. 폴더를 IntelliJ IDEA(Fabric 개발 가이드 추천)로 열거나, 터미널에서
   ```
   ./gradlew build
   ```
   를 실행합니다. (Windows는 `gradlew build`)
5. 빌드가 끝나면 `build/libs/halfheart-1.0.0.jar` 파일이 생성됩니다.
   이걸 `.minecraft/mods` 폴더에 넣고, **Fabric API**도 같은 mods 폴더에 넣어주세요.
   (Fabric API: https://modrinth.com/mod/fabric-api → 1.20.x용 다운로드)
6. Fabric Loader 1.20.1로 게임을 켜면 적용됩니다.

## 요구 사항
- Minecraft 1.20.1
- Fabric Loader
- Fabric API (필수, 별도 설치)
