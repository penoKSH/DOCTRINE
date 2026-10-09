# Build DoctrineValueSelector from Android / Termux

## Important
The project uses Android Gradle Plugin 8.6.1, Gradle 8.7, Java 17, and Android SDK Platform 35. The previous error `SDK location not found` means a valid Android SDK is not installed/configured. Installing `gradle` alone does not install the Android SDK. A Linux Android SDK setup is not reliably usable directly inside Android's Termux environment, so the included GitHub Actions workflow is the recommended no-PC build path.

## Prepare the project in Termux
Run these commands after placing `DoctrineValueSelector_Profile.zip` in `Download`:

```sh
pkg update && pkg upgrade -y
pkg install -y git unzip openssh
termux-setup-storage
mkdir -p ~/projects/DoctrineValueSelector
unzip -o /sdcard/Download/DoctrineValueSelector_Profile.zip -d ~/projects/DoctrineValueSelector
cd ~/projects/DoctrineValueSelector
```

## Recommended: build APK using GitHub Actions (no Android SDK needed on phone)
1. Create a new empty GitHub repository.
2. Upload the extracted project files, including the hidden `.github/workflows/android-build.yml` file. If using Git, run:

```sh
cd ~/projects/DoctrineValueSelector
git init
git add .
git commit -m "Prepare DoctrineValueSelector Android build"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPOSITORY.git
git push -u origin main
```

3. Open the repository on GitHub, go to **Actions**, select **Android APK Build**, and tap **Run workflow** if it has not started automatically.
4. When the workflow succeeds, open its run and download the artifact named `DoctrineValueSelector-debug-apk`. Extract it to get `app-debug.apk`.

## If building on a Linux computer
Install JDK 17 and Android SDK Platform 35 + Build Tools, set `ANDROID_HOME` to the SDK directory, then run `gradle --version` and `gradle --no-daemon assembleDebug` from the project root. Do not create `local.properties` with a guessed path; `sdk.dir` must point to a real SDK directory.
