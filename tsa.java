// tsa -> Task Scheduler Application


import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.Comparator;

public class tsa {

    // task data
    static final int MAX_TASKS = 100;

    static int[] id = new int[MAX_TASKS];
    static String[] name = new String[MAX_TASKS];
    static String[] description = new String[MAX_TASKS];
    static int[] priority = new int[MAX_TASKS];   // 1-High 2-Medium 3-Normal
    static int[] next = new int[MAX_TASKS];       // linked list "next" pointer (index), -1 = end

    static int head = -1;      // linked list
    static int freeIndex = 0;  // arrays
    static int idCounter = 1;  //unique id

   
    static Stack<Integer> completedStack = new Stack<>(); // undo
    static ArrayList<Integer> completedList = new ArrayList<>(); // completed history

    public static void main(String[] args) {
        Scanner tsa = new Scanner(System.in);


    System.out.println();
    System.out.println();




System.out.println("=======================================================================================================================================");

System.out.println();
        System.out.println("--------------------------Hi I am your Task Scheduler Application.--------------------------");
     System.out.println();
        System.out.print("Do you want more information about me? (yes/no): ");
        String response = tsa.nextLine();
    System.out.println();

System.out.println("=======================================================================================================================================");




        if (response.equalsIgnoreCase("yes")) {
    System.out.println();
    System.out.println();
    System.out.println();




System.out.println("=======================================================================================================================================");

    System.out.println();
            System.out.println("-----------------You can easily plan and list all the tasks you need to do throughout the day.-----------------\n");
            System.out.println("-----------------Once you finish a task, you can mark it as \"Done\" to easily track what is completed and what is left.-----------------\n");
            System.out.println("-----------------You can categorize your tasks based on urgency so you know what to focus on first:-----------------");
            System.out.println("************** 1) High Priority (Important): Crucial tasks that need immediate attention. **************");
            System.out.println("************** 2) Medium Priority: Important tasks that can be done next. **************");
            System.out.println("************** 3) Normal Priority: Routine or casual tasks with no rush. **************\n");
            System.out.println("-----------------It helps you organize your day perfectly, ensures you never forget any important work, and saves your time.-----------------");
    System.out.println();

System.out.println("=======================================================================================================================================");

        }




        boolean running = true;
        while (running) {
    System.out.println();
    System.out.println();
    System.out.println();

System.out.println("=======================================================================================================================================");

            System.out.println();
            System.out.println("====================== Let's start inputting your tasks. ======================");
            System.out.println("1. Enter Your Tasks");
            System.out.println("2. See all your tasks");
            System.out.println("3. Priority order Schedule");
            System.out.println("4. Mark a task as complete (Done)");
            System.out.println("5. Undo last completed task");
            System.out.println("6. See completed tasks history");
            System.out.println("7. Exit");
            System.out.println("===============================================================================");
        System.out.println();




System.out.println("=======================================================================================================================================");
    System.out.println();

            System.out.print("What do you want to do? (1-7): ");

    


            int choice;
            try {
                choice = Integer.parseInt(tsa.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 7.");
                continue;
            }



    
System.out.println("=======================================================================================================================================");





            switch (choice) {

                case 1: {
                    if (freeIndex >= MAX_TASKS) {
                        System.out.println("Sorry, task limit (" + MAX_TASKS + ") ended.");
                        break;
                    }

                    System.out.print("enter your task name: ");
                    String tName = tsa.nextLine();

                    System.out.print("Task description: ");
                    String tDesc = tsa.nextLine();

                    int tPriority = 0;
                    boolean validPriority = false;
                    while (!validPriority) {
                        System.out.print("Priority (1-High, 2-Medium, 3-Normal): ");
                        try {
                            tPriority = Integer.parseInt(tsa.nextLine().trim());
                            if (tPriority >= 1 && tPriority <= 3) {
                                validPriority = true;
                            } else {
                                System.out.println("only 1, 2, or 3.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("please enter a valid number (1, 2, or 3).");
                        }
                    }

                    int newId = addTaskToList(tName, tDesc, tPriority);
                    System.out.println("'" + tName + "' task ID " + newId + " added successfully with priority " + priorityText(tPriority) + ".");
                    break;
                }

                case 2: {
                    System.out.println();
                    System.out.println("-----------------See All Tasks-----------------");
                    displayAllTasks();
                    break;
                }

                case 3: {
                    showScheduleByPriority();
                    break;
                }

                case 4: {
                    if (head == -1) {
                        System.out.println("no tasks to mark as complete.");
                        break;
                    }
                    System.out.print("Enter the task id to mark as complete: ");
                    try {
                        int cid = Integer.parseInt(tsa.nextLine().trim());
                        int index = findIndexById(cid);
                        if (index == -1) {
                            System.out.println("Task with ID " + cid + " not found.");
                        } else {
                            removeTaskById(cid);
                            completedStack.push(index);   // Stack push 
                            completedList.add(index);     // ArrayList history
                            System.out.println("'" + name[index] + "' task marked as complete.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid task ID.");
                    }
                    break;
                }

                case 5: {
                    if (completedStack.isEmpty()) {
                        System.out.println("No tasks to undo.");
                    } else {
                        int index = completedStack.pop(); // Stack last complete
                        completedList.remove(Integer.valueOf(index)); // ArrayList
                        relinkExistingTask(index); //Linked List
                        System.out.println("'" + name[index] + "' reactivated and added back to your tasks.");
                    }
                    break;
                }

                case 6: {
                    if (completedList.isEmpty()) {
                        System.out.println("No completed tasks to display.");
                    } else {
                        System.out.println();
                        System.out.println("--------------- Completed Tasks History ---------------");
                        for (int index : completedList) {
                            System.out.println("ID: " + id[index] + " | " + name[index] + " | Priority: " + priorityText(priority[index]));
                        }
                    }
                    break;
                }

                case 7: {
                    System.out.println("Thank you for using the Task Scheduler Application.");
                    running = false;
                    break;
                }

                default:
                    System.out.println("please enter a valid option (1-7).");
            }
        }

        tsa.close();
    }

    

    static int addTaskToList(String n, String d, int p) {
        int index = freeIndex;

        id[index] = idCounter;
        name[index] = n;
        description[index] = d;
        priority[index] = p;
        next[index] = -1;

        if (head == -1) {
            head = index;
        } else {
            int curr = head;
            while (next[curr] != -1) {
                curr = next[curr];
            }
            next[curr] = index;
        }

        freeIndex++;
        idCounter++;
        return id[index];
    }

    static void relinkExistingTask(int index) {
        next[index] = -1;
        if (head == -1) {
            head = index;
        } else {
            int curr = head;
            while (next[curr] != -1) {
                curr = next[curr];
            }
            next[curr] = index;
        }
    }

    static boolean removeTaskById(int taskId) {
        if (head == -1) return false;

        if (id[head] == taskId) {
            head = next[head];
            return true;
        }

        int curr = head;
        while (next[curr] != -1) {
            int nxt = next[curr];
            if (id[nxt] == taskId) {
                next[curr] = next[nxt];
                return true;
            }
            curr = nxt;
        }
        return false;
    }

    static int findIndexById(int taskId) {
        int curr = head;
        while (curr != -1) {
            if (id[curr] == taskId) return curr;
            curr = next[curr];
        }
        return -1;
    }


    static void displayAllTasks() {
        if (head == -1) {
            System.out.println("no tasks to display.");
            return;
        }
        int curr = head;
        while (curr != -1) {
            System.out.println("Task ID        : " + id[curr]);
            System.out.println("Name           : " + name[curr]);
            System.out.println("Description    : " + description[curr]);
            System.out.println("Priority       : " + priorityText(priority[curr]));
            System.out.println("--------------------------------------------------");
            curr = next[curr];
        }
    }

    static void showScheduleByPriority() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(i -> priority[i]));
        System.out.println();
        System.out.println("-----------------Schedule Order (High -> Normal)-----------------");
        int curr = head;
        while (curr != -1) {
            pq.add(curr);
            curr = next[curr];
        }

        if (pq.isEmpty()) {
            System.out.println("no tasks to schedule.");
            return;
        }

        System.out.println();
        System.out.println("---------- Schedule Order (High -> Normal) ----------");
        int rank = 1;
        while (!pq.isEmpty()) {
            int i = pq.poll();
            System.out.println(rank + ". [" + priorityText(priority[i]) + "] " + name[i] + " (ID: " + id[i] + ")");
            rank++;
        }
    }

    static String priorityText(int p) {
        if (p == 1) return "High";
        else if (p == 2) return "Medium";
        else return "Normal";
    }
}