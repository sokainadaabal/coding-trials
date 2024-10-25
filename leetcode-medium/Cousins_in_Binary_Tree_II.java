class Solution {
    public TreeNode replaceValueInTree(TreeNode root) {
        if (root == null) return null;

        // File d'attente pour parcourir les niveaux de l'arbre
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        // Initialiser le niveau racine à 0 (car il n'a pas de cousins)
        root.val = 0;
        
        // Parcourir l'arbre niveau par niveau
        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            
            // Calcul de la somme de tous les nœuds du niveau actuel
            List<TreeNode> currentLevel = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node);
                
                if (node.left != null) {
                    queue.add(node.left);
                    levelSum += node.left.val;  // Ajouter la valeur de l'enfant gauche
                }
                if (node.right != null) {
                    queue.add(node.right);
                    levelSum += node.right.val;  // Ajouter la valeur de l'enfant droit
                }
            }
            
            // Remplacer les valeurs des nœuds du niveau actuel par la somme des cousins
            for (TreeNode node : currentLevel) {
                int childrenSum = 0;
                if (node.left != null) {
                    childrenSum += node.left.val;  // Somme des enfants directs
                }
                if (node.right != null) {
                    childrenSum += node.right.val;  // Somme des enfants directs
                }
                
                // Pour chaque nœud, la nouvelle valeur est la somme du niveau moins ses enfants directs
                if (node.left != null) {
                    node.left.val = levelSum - childrenSum;
                }
                if (node.right != null) {
                    node.right.val = levelSum - childrenSum;
                }
            }
        }
        
        return root;
    }
}