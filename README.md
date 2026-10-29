# Contacts Sharing App

A minimal Android application built with **Kotlin** and **Jetpack Compose** that demonstrates how to read and display device contacts using Android's **Contacts Content Provider**.

## Features

- Read contacts stored on the device.
- Display contact names and phone numbers in a clean, minimal interface.
- Tap a contact to view its phone number and email address.
- Request `READ_CONTACTS` permission at runtime.
- Refresh the contact list.
- Handle denied permissions and empty contact lists.

## Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Design:** Material 3
- **Android API:** `ContactsContract` and `ContentResolver`
- **Build system:** Gradle
## Screenshots
<img width="608" height="1320" alt="WhatsApp Image 2026-10-15 at 11 17 50 AM" src="https://github.com/user-attachments/assets/18520194-375e-4f9e-a732-a7f5274f6903" />
<img width="1216" height="2640" alt="WhatsApp Image 2026-10-15 at 11 17 51 AM" src="https://github.com/user-attachments/assets/697ac08c-565c-4465-8e31-13bdcf757c90" />

## How It Works

The app uses Android's built-in Contacts Content Provider instead of maintaining a separate contacts database.

1. The app requests the user's permission to read contacts.
2. `ContentResolver.query()` retrieves contact records from `ContactsContract.Contacts.CONTENT_URI`.
3. Phone numbers and email addresses are queried through:
   - `ContactsContract.CommonDataKinds.Phone.CONTENT_URI`
   - `ContactsContract.CommonDataKinds.Email.CONTENT_URI`
4. The app displays the retrieved information using Jetpack Compose.

## Project Structure

```text
ContactsSharing/
└── app/
    └── src/
        └── main/
            ├── AndroidManifest.xml
            └── java/com/example/contactssharing/
                └── MainActivity.kt
```

> The package path above is an example. If your Android Studio project uses a different package name, update the path accordingly.

## Requirements

- Android Studio
- Android SDK installed through Android Studio
- A device or emulator with Android contacts available
- Internet access for the initial Gradle dependency sync

## Run the App

1. Clone or download this repository.
2. Open the project in Android Studio.
3. Allow Gradle to sync and download dependencies.
4. Run the app on an Android emulator or a physical Android device.
5. Grant Contacts permission when prompted.
6. Add contacts to the emulator first if the list is empty.

## Permission

The app requires this permission in `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.READ_CONTACTS" />
```

Contacts are accessed only after the user grants permission. This sample reads contact information; it does not add, edit, or delete contacts.

## Learning Outcomes

This project demonstrates:

- Android Content Providers
- `ContentResolver`
- `ContactsContract`
- Runtime permissions
- Querying data using cursors
- Building a simple UI with Jetpack Compose

## Future Improvements

- Add contact search.
- Display contact profile photos.
- Support multiple phone numbers and email addresses per contact.
- Move contact queries off the main thread for better performance.
- Add automated tests.

## License

This project is available for educational and learning purposes. Add a license file if you intend to distribute or reuse it under specific terms.
