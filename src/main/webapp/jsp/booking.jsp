<html>
    <body>
        <form action="logout" method="post">
            <input type="submit" value="Log out">
        </form>
        <h2>My greetings.</h2>
        <h2>Greetings, traveler.</h2>
        <h2>Hello.</h2>
        <h2>Well met!</h2>
        <h2>Greetings.</h2>
        <h2>The pleasure is mine.</h2>
        <h2>Greetings, friend.</h2>
        <h2>I greet you.</h2>
        <h2>Heh, greetings.</h2>
        <form action="booking" method="post">
            <h5>place number</h5>
            <input type="number" name="place-number">
            <h5>room class</h5>
            <select name="room-class">
                <option>A+</option>
                <option>A</option>
                <option>B</option>
            </select>
            <h5>date from</h5>
            <input type="datetime-local" name="date-from">
            <h5>date to</h5>
            <input type="datetime-local" name="date-to">
            <h5>room (may be empty)</h5>
            <select name="room">
                <!-- ??????? -->
            </select>
            <input type="submit" value="Book">
        </form>

    </body>
</html>