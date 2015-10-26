package org.jupiter.monitor;

import org.jupiter.common.util.Maps;

import java.util.Map;

import static org.jupiter.monitor.Command.ChildCommand.REPORT;

/**
 * jupiter
 * org.jupiter.monitor
 *
 * @author jiachun.fjc
 */
public enum Command {
    AUTH("Login with password"),
    HELP("Help information"),
    METRICS("Metrics", REPORT),
    QUIT("Quit monitor")
    ;

    private String description;
    private ChildCommand[] children;

    Command(String description, ChildCommand... children) {
        this.description = description;
        this.children = children;
    }

    public String description() {
        return description;
    }

    public ChildCommand[] children() {
        return children;
    }

    public ChildCommand parseChild(String childName) {
        for (ChildCommand c : children()) {
            if (c.name().equalsIgnoreCase(childName)) {
                return c;
            }
        }
        return null;
    }

    public static Command parse(String name) {
        return commands.get(name.toLowerCase());
    }

    private static final Map<String, Command> commands = Maps.newHashMap();
    static {
        for (Command c : Command.values()) {
            commands.put(c.name().toLowerCase(), c);
        }
    }

    public enum ChildCommand {
        REPORT("Report the current values of all metrics in the registry.")
        ;

        private String description;

        ChildCommand(String description) {
            this.description = description;
        }

        public String description() {
            return description;
        }
    }
}
