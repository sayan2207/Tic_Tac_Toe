import java.util.Scanner;

class Main extends DeveloperTTT
{
    private char[][] grid;
    private int turn;
    private int level;
    private double p;
    private int cmove=0;
    private int[] lastmove={0,0};

    private Main()
    {
        grid=new char[3][3];
        turn=1;
        level=1;
        p=0.1;
    }

    public static void main(String args[])throws Exception
    {
        Main ob=new Main();

        ob.startGame();
    }

    private void chooseLevel()
    {
        Scanner sc=new Scanner(System.in);
        do
        {
            System.out.println("Choose your level");
            System.out.println("1. Easy\n2. Medium\n3. Difficult\n4. Play against Darth_Vader (He starts first)\n WARNING:#4 is not suggested\n\n1 / 2 / 3 / 4 ???\n");
            level=sc.nextInt();

            System.out.print("\u000c");
        }
        while(level>4||level<1);
        if(level==1)
            p=2p1;
        if(level==2)
            p=p2;
        if(level==3)
            p=p3;
    }

    private void startGame()
    {
        turn=(int)(Math.random())+1;
        chooseLevel();
        preArrange();

        if(level==4)
        {
            Darth_VaderTurn();
            return;
        }

        while(GameContinues())
        {
            if(turn++%2==1)
            {
                display();
                humanTurn();

                if(check(grid))
                {
                    display();
                    System.out.println("\n\nHuman won!!");
                    return;
                }
            }
            else
            {
                display();
                if(level<4)
                    aiTurn();
                else
                    Darth_VaderTurn();
                cmove++;
                if(check(grid))
                {
                    display();
                    System.out.println("\n\nComputer won!!");
                    return;
                }
            }
        }

        display();
        System.out.println("\nNobody won. It's a tie.\n:-)");
    }

    private void display()
    {
        System.out.println("\u000c");
        System.out.println();
        System.out.print("   ");

        for(int i=0;i<3;i++)
        {
            System.out.print(" "+i+"  ");
        }
        System.out.println("\n");
        for(int i=0;i<3;i++)
        {
            System.out.print(i+"  ");

            for(int j=0;j<3;j++)
            {
                System.out.print(" "+grid[i][j]+" ");
                if(j!=2)
                {
                    System.out.print("|");
                }
            }
            if(i!=2)
            {
                System.out.println("\n    ----------");
            }
        }
    }

    private boolean check(char[][] grid2)
    {

        for(int i=0;i<3;i++)
        {
            if(grid2[i][0]==grid2[i][1]&&grid2[i][1]==grid2[i][2]&&grid2[i][2]!=' ')
            {
                return true;
            }

            if(grid2[0][i]==grid2[1][i]&&grid2[1][i]==grid2[2][i]&&grid2[2][i]!=' ')
            {
                return true;
            }
        }

        if(grid2[0][0]==grid2[1][1]&&grid2[1][1]==grid2[2][2]&&grid2[2][2]!=' ')
        {
            return true;
        }

        if(grid2[0][2]==grid2[1][1]&&grid2[1][1]==grid2[2][0]&&grid2[0][2]!=' ')
        {
            return true;
        }

        return false;
    }

    private  void preArrange()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                grid[i][j]=' ';
            }
        }
    }

    private boolean GameContinues()
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                if(grid[i][j]==' ')
                {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean valid(int a, int b)
    {
        if(a<0||a>3)
        {
            return false;
        }

        if(b<0||b>3)
        {
            return false;
        }

        if(grid[a][b]==' ')
        {
            return true;
        }

        return false;
    }

    private void aiIsFool()
    {
        int a,b;

        do
        {
            a=(int)(Math.random()*3);

            b=(int)(Math.random()*3);
        }while(!valid(a,b));

        grid[a][b]=ai;
    }

    private void aiIsSmart()
    {
        int a,b;

        do
        {
            a=(int)(Math.random()*3);

            b=(int)(Math.random()*3);
        }while(!valid(a,b));

        for(int i=0;i<3;i++)
        {
            for(int j=2;j>=0;j--)
            {
                if(grid[i][j]==' '&&preCheckW(i,j))
                {
                    grid[i][j]=ai;
                    return;
                }
            }
        }

        for(int i=0;i<3;i++)
        {
            for(int j=2;j>=0;j--)
            {
                if(grid[i][j]==' '&&preCheckL(i,j))
                {
                    grid[i][j]=ai;
                    return;
                }
            }
        }

        grid[a][b]=ai;
    }

    private void aiTurn()
    {
        String N[]={"fool's move","smarty move"};
        double P[]={(1-p),p};
        int k=0,random;

        String N2[]=new String[100];

        for(int i=0;i<N.length;i++)
        {
            for(int j=0;j<(int)(P[i]*100);j++)
            {
                N2[k++]=N[i];
            }
        }

        random=(int)(Math.random()*N2.length);
        if(N2[random].equals("fool's move"))
            aiIsFool();
        else
            aiIsSmart();
    }



    private void humanTurn()
    {

        Scanner sc=new Scanner(System.in);

        System.out.print("\n\nEnter your Co-ordinates: ");
        lastmove[0]=sc.nextInt();
        lastmove[1]=sc.nextInt();

        while(!valid(lastmove[0],lastmove[1]))
        {
            System.out.println("Co-ordinates were invalid.");
            System.out.print("Enter again: ");
            lastmove[0]=sc.nextInt();
            lastmove[1]=sc.nextInt();
        }

        grid[lastmove[0]][lastmove[1]]=h;

    }

    private boolean preCheckW(int a,int b)
    {

        char[][] temp=new char[3][3];

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                temp[i][j]=grid[i][j];
            }
        }

        temp[a][b]=ai;

        return check(temp);

    }

    private boolean preCheckL(int a, int b)
    {

        char[][] temp=new char[3][3];

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                temp[i][j]=grid[i][j];
            }
        }

        temp[a][b]=h;

        return check(temp);

    }

    //int Darth_Vaderc=(int)(Math.random()*2);
    int Darth_Vaderc=1;
    public void Darth_VaderTurn()
    {

        int mymove[]={0,0};
        if(Darth_Vaderc==1)
        {
            grid[1][1]=ai;

            display();
            humanTurn();

            if(lastmove[0]==1||lastmove[1]==1)
            {
                mymove=farCorner(lastmove);
                grid[mymove[0]][mymove[1]]=ai;

                display();
                humanTurn();

                aiIsSmart();
                if(check(grid))
                {
                    display();
                    System.out.println("\n\nDarth_Vader won!!");
                    return;
                }

                display();
                humanTurn();

                aiIsSmart();

                display();
                System.out.println("\n\nDarth_Vader won!!");
                return;

                // VICTORY
            }
            else
            {
                if(lastmove[0]==0)
                    mymove[0]=2;
                else
                    mymove[0]=0;
                if(lastmove[1]==0)
                    mymove[1]=2;
                else
                    mymove[1]=0;

                grid[mymove[0]][mymove[1]]=ai;

                int[] lastmove2=lastmove;

                display();
                humanTurn();

                if(lastmove[0]==1||lastmove[1]==1)
                {
                    //VICTORY

                    int a=-1,b=-1;

                    for(int i=0;i<3;i++)
                    {
                        for(int j=2;j>=0;j--)
                        {
                            if(grid[i][j]==' '&&preCheckW(i,j))
                            {
                                grid[i][j]=ai;
                                return;
                            }
                        }
                    }

                    for(int i=0;i<3;i++)
                    {
                        for(int j=2;j>=0;j--)
                        {
                            if(grid[i][j]==' '&&preCheckL(i,j))
                            {
                                grid[i][j]=ai;
                                return;
                            }
                        }
                    }
                    if(a!=-1)
                        grid[a][b]=ai;
                    else
                    {
                        //VICTORY
                        if(valid(0,0)&&(lastmove[0]!=0&&lastmove[1]!=0))
                        {mymove[0]=0;mymove[1]=0;}
                        else if(valid(2,0)&&(lastmove[0]!=2&&lastmove[1]!=0))
                        {mymove[0]=2;mymove[1]=0;}
                        else if(valid(0,2)&&(lastmove[0]!=0&&lastmove[1]!=2))
                        {mymove[0]=0;mymove[1]=2;}
                        else
                        {mymove[0]=2;mymove[1]=2;}

                        grid[mymove[0]][mymove[1]]=ai;
                    }
                    display();
                    humanTurn();

                    aiIsSmart();

                    display();
                    System.out.println("\n\nDarth_Vader won!!");
                    return;

                }

                else
                {
                    if(lastmove[0]==lastmove2[0])
                    {mymove[0]=lastmove[0];mymove[1]=1;}
                    else
                    {mymove[0]=1;mymove[1]=lastmove[1];}

                    grid[mymove[0]][mymove[1]]=ai;

                    display();
                    humanTurn();

                    while(true)
                    {
                        aiIsSmart();

                        if(check(grid))
                        {
                            display();
                            System.out.println("\n\nDarth_Vader won!!");
                            return;
                        }

                        if(checkTie(grid))
                        {
                            display();
                            System.out.println("\n\n Nobody won!!");
                            return;
                        }
                        display();
                        humanTurn();
                    }
                }
            }

        }
        else
        {
            return;
        }
    }

    private boolean checkTie(char[][] grid)
    {
        for(int i=0;i<grid.length;i++)
            for(int j=0;j<grid[0].length;j++)
                if(grid[i][j]==' ')
                    return false;
        return true;
    }

    private boolean contact(int a[],int b[])
    {
        if(a[0]!=b[0]&&a[1]!=b[1])
            return false;
        if(a[0]==b[0])
            if(Math.abs(a[1]-b[1])==1)
                return true;
        if(a[1]==b[1])
            if(Math.abs(a[0]-b[0])==1)
                return true;
        return false;
    }

    private int[] farCorner(int[] m)
    {
        int n[]={0,0};
        if(m[0]==1&&m[1]==0)
        {
            if((int)(Math.random()*2)==1)
            {n[0]=0;n[1]=2;}
            else
            {n[0]=2;n[1]=2;}
        }
        else if(m[0]==1&&m[1]==2)
        {
            if((int)(Math.random()*2)==1)
            {n[0]=0;n[1]=0;}
            else
            {n[0]=2;n[1]=0;}
        }
        else if(m[0]==0&&m[1]==1)
        {
            if((int)(Math.random()*2)==1)
            {n[0]=2;n[1]=0;}
            else
            {n[0]=2;n[1]=2;}
        }
        else
        {
            if((int)(Math.random()*2)==1)
            {n[0]=0;n[1]=0;}
            else
            {n[0]=0;n[1]=2;}
        }
        return n;
    }

}
