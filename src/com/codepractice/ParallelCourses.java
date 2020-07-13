class DAG {
    int totalNodes;
    Map<Integer, GraphNode> nodes;

    public DAG(int totalNodes){
        this.totalNodes = totalNodes;
        this.nodes = new HashMap<>();
    }

    public Map<Integer, GraphNode> getNodes(){
        return this.nodes;
    }

    public GraphNode addNode(int node){
        GraphNode graphNode = this.nodes.get(node);
        if(graphNode==null){
            graphNode = new GraphNode(node);
            System.out.println("Adding :: "+node);
            this.nodes.put(node, graphNode);
            return this.nodes.get(node);
        }else{
            return graphNode;
        }
    }

    public void buildGraph(int[][] dependencies, int n){
        System.out.println("Row length of dependencies :: "+ dependencies.length);
        for(int i=0; i < dependencies.length;i++){
            System.out.println("iteration for ::: "+ dependencies[i][1]+ " "+ dependencies[i][0]);
            GraphNode to = this.addNode(dependencies[i][1]);
            GraphNode from = this.addNode(dependencies[i][0]);
            to.addDependency(from);
        }
        
        if(this.nodes.keySet().size() < n){ // add all the courses with no in or out dependency
            for(int i=1;i<=n;i++){
                if(!this.nodes.keySet().contains(i)){
                    this.addNode(i);
                }
            }
        }
        
    }
    
    public int getHangingNodes(){
        return this.totalNodes - this.nodes.keySet().size();
    }

}

class GraphNode{
    int in;
    int data;
    boolean visited;
    Map<Integer, GraphNode> dependencies;

    public GraphNode(int n){
        this.data = n;
        this.in = 0;
        this.dependencies = new HashMap<>();
    }

    public int getIn(){
        return this.in;
    }

    public void incrementInDegree(){
        this.in += 1;
    }

    public void decrementInDegree(){
        this.in -= 1;
    }

    public void addDependency(GraphNode dependency){
        this.incrementInDegree();
        this.dependencies.put(dependency.data, dependency);
    }

    public boolean hasDependency(GraphNode dependency){
        return this.dependencies.containsKey(dependency.data);
    }

    public void markVisited(){
        this.visited = true;
    }
}
public class ParallelCourses {

    public static Deque<GraphNode> fillUpSemester(Deque<GraphNode> nodes, int k){
        Deque<GraphNode> semCourses = new ArrayDeque<>(k);
        for(int i=0;i<k;i++){
            if(!nodes.isEmpty()){
                semCourses.push(nodes.poll());
            }
        }
        return semCourses;
    }

    public static int minNumberOfSemesters(int n, int[][] dependencies, int k) {
        
        if(dependencies.length==0){
            return n/k + (n%k>0 ? 1: 0);
        }
        
        DAG graph = new DAG(n);
        graph.buildGraph(dependencies, n);

        int sem = 1;
        Deque<GraphNode> inNodes = new ArrayDeque<>();

        for(GraphNode node : graph.getNodes().values()){
            if(node.getIn()==0){
                node.markVisited();
                inNodes.push(node);
            }
        }
        System.out.println("inNodes size = " + inNodes.size());
        Deque<GraphNode> semCourses = fillUpSemester(inNodes, k);
        while(!semCourses.isEmpty()){
            GraphNode dependency = semCourses.poll();
            System.out.println("Semester = " + sem + " Course=" + dependency.data);

            for(GraphNode course : graph.getNodes().values()){
                if(course.hasDependency(dependency)){
                    course.decrementInDegree();
                }

                if(course.getIn()==0 && !course.visited){
                    System.out.println("Pushing :::: " + course.data);
                    course.markVisited();
                    inNodes.push(course);
                }
            }

            if(semCourses.isEmpty() && !inNodes.isEmpty()){
                semCourses = fillUpSemester(inNodes, k);
                sem += 1;
            }
        }
        return sem;
    }
}
