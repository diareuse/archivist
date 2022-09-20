<p align="center">
    <img src="art/cover-art.svg" width="500px" />
</p>

<h1 align="center">Archivist</h1>

The motivation behind archiving your songs is in three major pillars.

### I. Statistics

Have you ever noticed that you're not getting your newer saved songs in your "unwrapped" by the end of the year? Well
it's because Spotify tries to suggest songs that are older and thinks you still would enjoy. Offers remixes and remakes
of the same songs instead of enjoying (and supporting) emerging creators and newer tracks.

This concerns you mainly if you use "shuffle" feature, which does this by default.

### II. Cleanliness

Personally I like to return back and listen to how my listening profile changes from year to year. This allows you to do
that, since the program creates yearly directories. Forth to the past!

### III. Irritation

When you listen to one particular song over and over again you'll get sooner or later irritated. It's not because you
don't like the song anymore (as in months or years for some people, you'll vibe with the song again!), it's purely due
to the nature of overfeeding yourself with one sequence of ever looping patterns. Your brain likes new things so you can
push it to the right direction.

---

### Setting up

You'll need to create a developer account at Spotify. Then use the credentials as input to this program. Run the program
and done!

#### Spotify Developer Credentials

Visit [Spotify for Developers][Spotify Dashboard] and log in.

> You should login with the account that you'll later use to archive your songs in. But it doesn't really matter, you'll
> still need to be authorized personally.

Create an app and name it "Archivist".

> The name doesn't matter, name it whatever you like really.

Once created head over to "Edit Settings". There you should change **only** the Redirect URIs. Change it
to: `https://spotify.depasquale.wiki/redirect`. Press Save.

> The url doesn't need to exists, you'll manually extract the authorization code later on.

Now you need to copy `Client ID` and `Client Secret`. The secret is hidden, so you need to press `Show Client Secret` in
order to make it visible.

#### Running the program

This is a Java-based program, so your system (or automation provider) needs to have Java installed. The easiest way is
to install JDK from [Adoptium].

> Follow the great [Installation Instructions][Install JDK] on Adoptium's website.

Now just check that your `java` executable is correctly installed by running

```zsh
java --version
```

You should see an output telling you the version of the Java you just installed.

#### Initialization

There's an one-time setup in order to retrieve access keys to your Spotify account. Don't worry, the process will run
entirely on your machine and the access will be granted to you personally. This repository or its owner will never
access or manipulate your data.

So! First [Download][Repository Download] the repository and extract it to a folder you'd like to run the program from.

> You'll need to run a few commands in the terminal, but it's easy I promise. If you encounter any problems open an
> issue on this repository.

Open a Terminal.

1) macOS users can call Spotlight and write "Terminal"
2) Windows users can press WIN+R and write "cmd"
3) Linux users know what to do

Change working directory

```zsh
cd archivist-master
```

> Tip: You can also write `cd ` and drag'n'drop the directory containing archivist to the Terminal. Most of modern
> terminals have this feature.

Make sure you're in the correct directory by running `ls` in the same Terminal

> You should see a file named `gradlew` or `gradlew.bat` for Windows users.

Build the project

1) Windows
   1) `gradlew shadowJar`
2) Linux/macOS
   1) `./gradlew shadowJar`

Run command to invoke the authorization. You will be asked to go to a link to authorize yourself and then collect a part
of url.

```zsh
java -jar app/build/libs/app-all.jar --user-id <your-user-name> --client-id <client-id> --client-secret <client-secret> --dry-run
```

> If you're not aware of your user-id visit [Account Overview][Spotify Account] and search for "Username". Use client-id
> and client-secret you've retrieved in one of the first steps. Obviously without the < or > characters.

> You're running a `--dry-run`. This means that no modification will be done to your account YET. It will only tell you
> what would it do if it were to do something. This approach is safe(r) since you might want to run this on other device
> after finishing setup.

After authorizing the application you should be redirected to a website that does not exist. That's okay though, we just
need the url. Copy the text **after** `?code=`. After that return back to the terminal and paste it there and press
Return or Enter.

> If you're new to the whole Terminal thing, don't worry. When the program says "Enter auth code:" (or something
> similar) you should see a cursor blinking below it. This means you're expected to enter some value and press Enter.

> Note: Please note that you might be required to enter the code multiple times, because some Terminals behave in other
> ways that others. Just paste the code and press Enter, if nothing happens for a long time, then do it again.

#### …and archive away!

Great! You finished the setup, now you can run the same command without the `--dry-run` directive and your tracks become
sorted in a few short moments.

```zsh
java -jar app/build/libs/app-all.jar --user-id <your-user-name> --client-id <client-id> --client-secret <client-secret> 
```

> Notice that `config.properties` file was created. Removing this file removes the authorization from the app and you
> will be required to repeat the process.

---

### Automate Periodically?

Periodic automation can be done by running the program on your own computer see [cron][Cron Guru] or use Github Actions
to run this job automatically on repeat.

#### Github Actions

1) [Fork][Repository Fork] this repository.
2) Go to Settings > Secrets > Actions
3) Enter these variables:
    1) Name: `ARCHIVIST_USER_ID` Secret: `<your-user-name>`
    2) Name: `ARCHIVIST_CLIENT_ID` Secret: `<client-id>`
    3) Name: `ARCHIVIST_CLIENT_SECRET` Secret: `<client-secret>`
4) Find your `config.properties` file and Base64 [encode][Base64 Encoder] it
5) Enter another variable:
    1) Name: `ARCHIVIST_CONFIG` Secret: `<base64-encoded-config.properties>`
6) Done!

Your repository will automatically by the start of each month cleanup your saved songs. If you ever change your mind
just delete the repository.

---

[Image by pch.vector][Attribution] on Freepik

[Attribution]: https://www.freepik.com/free-vector/book-readers-concept_9174332.htm

[Spotify Dashboard]: https://developer.spotify.com/dashboard/applications

[Spotify Account]: https://www.spotify.com/us/account/overview/

[Adoptium]: https://adoptium.net/

[brew]: https://brew.sh/

[Install JDK]: https://adoptium.net/installation

[Repository Download]: https://github.com/diareuse/archivist/archive/refs/heads/master.zip

[Repository Fork]: https://github.com/diareuse/archivist/fork

[Cron Guru]: https://crontab.guru

[Base64 Encoder]: https://www.base64encode.org/