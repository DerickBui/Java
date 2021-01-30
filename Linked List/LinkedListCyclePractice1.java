public class LinkedListCyclePractice1 {
  public static void main(String[] args) {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    Node node5 = new Node(5);
    Node node6 = new Node(6);
    Node node7 = new Node(7);

    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node5;
    node5.next = node6;
    node6.next = node7;
    node7.next = node4; //Cycle goes to node 4

    System.out.println(findCycle(node1));
  }

  public static boolean findCycle(Node head) {
    if (head == null) return false;

    Node pointer1 = head;
    Node pointer2 = head.next;

    while ((pointer1 != null) && (pointer2 != null) && (pointer2.next != null)) {
      if (pointer1 == pointer2) {
        return true;
      }

      pointer2 = pointer2.next.next;
      pointer1 = pointer1.next;
    }

    return false;
  }
}
